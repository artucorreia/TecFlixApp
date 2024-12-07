package br.com.tecflix_app.service.payment;

import org.json.JSONObject;

import br.com.tecflix_app.data.DTO.v1.create.PixChargeRequest;

public interface Pix {
    JSONObject createEVP();
    JSONObject createCharge(PixChargeRequest pixChargeRequest);
}
