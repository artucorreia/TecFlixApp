package br.com.tecflix_app.service.payment.contract;

import org.json.JSONObject;

import br.com.tecflix_app.data.DTO.v1.create.PixChargeRequest;

public interface IPixService {
    JSONObject createEVP();
    JSONObject getPixKey();
    JSONObject createCharge(PixChargeRequest pixChargeRequest);
}
