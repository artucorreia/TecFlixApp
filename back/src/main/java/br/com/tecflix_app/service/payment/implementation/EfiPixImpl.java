package br.com.tecflix_app.service.payment.implementation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.tecflix_app.config.payment.pix.Credentials;
import br.com.tecflix_app.data.DTO.v1.create.PixChargeRequest;
import br.com.tecflix_app.exception.payment.EVPGenerationException;
import br.com.tecflix_app.exception.payment.PixGenerationException;
import br.com.tecflix_app.exception.payment.QRCodeGenerationException;
// import br.com.tecflix_app.service.UserService;
// import br.com.tecflix_app.service.auth.jwt.TokenService;
import br.com.tecflix_app.service.payment.Pix;

@Primary
@Service
public class EfiPixImpl implements Pix {
    private final Logger LOGGER = Logger.getLogger(EfiPixImpl.class.getName());

    @Value("${payment.pix.efi.client.id}")
    private String clientId;
    
    @Value("${payment.pix.efi.client.secret}")
    private String clientSecret;
    
    @Value("${payment.pix.efi.key}")
    private String randomKey;

    /*
        TODO: Add user info em charge body
        TODO: Alter adtional info
    */
    
    // private final TokenService tokenService;
    // private final UserService userService;

    // @Autowired
    // public EfiPixImpl(
    //     TokenService tokenService,
    //     UserService userService
    
    // ) {
    //     this.tokenService = tokenService;
    //     this.userService = userService;
    // }


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
    public JSONObject createCharge(PixChargeRequest pixChargeRequest) {
        LOGGER.info("Creating charge");

        JSONObject options = configuringJsonObject();
        JSONObject body = createChargeBody(pixChargeRequest);
        JSONArray infoAdicionais = createAdditionalInfo(); 
        body.put("infoAdicionais", infoAdicionais);

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<String, String>(), body);
            int id = response.getJSONObject("loc").getInt("id");
            pixGenerateQRCode(String.valueOf(id));        
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
            "devedor",
            new JSONObject().put(
                "cpf",
                "12345678909"
            ).put(
                "nome",
                "Francisco da Silva"
            )
        );
        body.put(
            "valor", 
            new JSONObject().put(
                "original", 
                pixChargeRequest.getPrice()
            )
        );
        body.put("chave", randomKey);
        return body;
    }

    private JSONArray createAdditionalInfo() {
        LOGGER.info("Creating additional info");
        
        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(
            new JSONObject()
                .put("nome", "Campo 1")
                .put("valor", "Informação Adicional1 do PSP-Recebedor")
            );
        infoAdicionais.put(
            new JSONObject()
                .put("nome", "Campo 2")
                .put("valor", "Informação Adicional2 do PSP-Recebedor")
            );
        return infoAdicionais;
    }

    private void pixGenerateQRCode(String id) {
        JSONObject options = configuringJsonObject();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        try {
            EfiPay efi= new EfiPay(options);
            Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<String, Object>());

            File outputfile = new File("qrCodeImage.png");
            ImageIO.write(ImageIO.read(new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(((String) response.get("imagemQrcode")).split(",")[1]))), "png", outputfile);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(outputfile);
        }
        catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new QRCodeGenerationException(e.getError());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new QRCodeGenerationException("Ocorreu um erro inesperado durante a geração do QRCode");
        }
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
