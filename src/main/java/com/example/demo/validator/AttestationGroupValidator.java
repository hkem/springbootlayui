package com.example.demo.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Map;

public class AttestationGroupValidator {

    public int attestation_group_id;
    @NotNull(message = "名称不能为空")
    public String group_name;
    @NotNull(message = "权限不能为空")
    public String attestation_id;
    public int getAttestation_group_id() {
        return attestation_group_id;
    }

    public void setAttestation_group_id(int attestation_group_id) {
        this.attestation_group_id = attestation_group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getAttestation_id() {
        return attestation_id;
    }

    public void setAttestation_id(String attestation_id) {
        this.attestation_id = attestation_id;
    }
}
