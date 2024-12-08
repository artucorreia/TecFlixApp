package br.com.tecflix_app.service.payment.implementation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.tecflix_app.config.payment.pix.Credentials;
import br.com.tecflix_app.data.DTO.v1.create.PixChargeRequest;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.exception.payment.EVPGenerationException;
import br.com.tecflix_app.exception.payment.PixGenerationException;
import br.com.tecflix_app.exception.payment.QRCodeGenerationException;
import br.com.tecflix_app.service.UserService;
import br.com.tecflix_app.service.auth.jwt.TokenService;
import br.com.tecflix_app.service.payment.Pix;
import br.com.tecflix_app.service.util.FileNameGenerator;

@Primary
@Service
public class EfiPixImpl implements Pix {
    private final Logger LOGGER = Logger.getLogger(EfiPixImpl.class.getName());

    @Value("${payment.pix.efi.client.id}")
    private String clientId;
    
    @Value("${payment.pix.efi.client.secret}")
    private String clientSecret;
    
    @Value("${payment.pix.efi.key}")
    private String pixKey;
    
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public EfiPixImpl(
        TokenService tokenService,
        UserService userService
    
    ) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    public JSONObject createEVP() {
        LOGGER.info("Creating EVP");

        JSONObject options = configuringJsonObject();
        try {
            EfiPay efi = new EfiPay(options);
            return efi.call("pixCreateEvp", new HashMap<String,String>(), new JSONObject());
        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new EVPGenerationException(e.getError());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new EVPGenerationException("Ocorreu um erro inesperado na geração da EVP");
        }
    }

    @Override
    public JSONObject getPixKey() {
        LOGGER.info("Getting pix key");
        if (pixKey == null) return createEVP();
        
        return new JSONObject().put("chave", pixKey);
    }
    
    @Override
    public JSONObject createCharge(PixChargeRequest pixChargeRequest) {
        LOGGER.info("Creating charge");

        UserDTO user = userService.findById(tokenService.getUserId());

        JSONObject options = configuringJsonObject();
        JSONObject body = createChargeBody(pixChargeRequest);
        JSONArray infoAdicionais = createAdditionalInfo(user.getId().toString()); 
        body.put("infoAdicionais", infoAdicionais);

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<String, String>(), body);
            int id = response.getJSONObject("loc").getInt("id");
            pixGenerateQRCode(String.valueOf(id), user.getId().toString());        
            return response;
        }
        catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new PixGenerationException(e.getError());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PixGenerationException("Ocorreu um erro inesperado durante a geração do pagamento");
        }
    }

    private JSONObject createChargeBody(PixChargeRequest pixChargeRequest) {
        LOGGER.info("Creating charge body");
        
        JSONObject body = new JSONObject();
        body.put(
            "calendario", 
            new JSONObject().put(
                "expiracao",
                3600
            )
        );
        body.put(
            "valor", 
            new JSONObject().put(
                "original", 
                pixChargeRequest.getValue()
            )
        );
        body.put("chave", pixChargeRequest.getKey());
        return body;
    }

    private JSONArray createAdditionalInfo(String userId) {
        LOGGER.info("Creating additional info");
        
        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(
            new JSONObject()
                .put("nome", "ID Usuário")
                .put("valor", userId)
        );
        infoAdicionais.put(
            new JSONObject()
                .put("nome", "Propósito")
                .put("valor", "Apoio ao desenvolvimento da plataforma TecFlix")
        );
        infoAdicionais.put(
            new JSONObject()
                .put("nome", "Mensagem")
                .put("valor", "Sua contribuição faz a TecFlix continuar crescendo!")
        );
        return infoAdicionais;
    }

    private void pixGenerateQRCode(String id, String userId) {
        LOGGER.info("Creating QR Code");
        
        JSONObject options = configuringJsonObject();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        try {
            EfiPay efi= new EfiPay(options);
            Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<String, Object>());

            String fileName = generateFileName(id, userId, LocalDateTime.now());

            File outputfile = new File("qrcodes/"+ fileName +".png");
            ImageIO.write(ImageIO.read(new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(((String) response.get("imagemQrcode")).split(",")[1]))), "png", outputfile);

            LOGGER.info("QR Code path: " + outputfile.getAbsolutePath());

            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(outputfile);
            } else {
                System.out.println("O ambiente está em modo headless. Não é possível abrir o QRCode.");
            }            
        }
        catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new QRCodeGenerationException(e.getError());
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            throw new QRCodeGenerationException("Ocorreu um erro inesperado durante a geração do QRCode");
        }
    }

    private String generateFileName(String id, String userId, LocalDateTime timestamp) {
        String[] strings = new String[]{id, userId, LocalDateTime.now().toString()};
        return FileNameGenerator.generateFileName(strings);
    }

    private JSONObject configuringJsonObject(){
        Credentials credentials = new Credentials();
        JSONObject options = new JSONObject();
        options.put("client_id", clientId);
        options.put("client_secret", clientSecret);
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());
        return options;
    }
}
