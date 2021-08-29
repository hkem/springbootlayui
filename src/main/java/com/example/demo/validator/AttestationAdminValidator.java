package com.example.demo.validator;

import javax.validation.constraints.NotNull;

public class AttestationAdminValidator {


    public int attestation_admin_id;
    @NotNull(message = "管理员不能为空")
    public int attestation_group_id;
    @NotNull(message = "权限不能为空")
    public int admin_id;

    public int getAttestation_admin_id() {
        return attestation_admin_id;
    }

    public void setAttestation_admin_id(int attestation_admin_id) {
        this.attestation_admin_id = attestation_admin_id;
    }

    public int getAttestation_group_id() {
        return attestation_group_id;
    }

    public void setAttestation_group_id(int attestation_group_id) {
        this.attestation_group_id = attestation_group_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
