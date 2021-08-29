package com.example.demo.entity;

public class AttestationAdmin {

    public int attestation_admin_id;
    public int attestation_group_id;
    public int admin_id;
    public String created_at;
    public String updated_at;


    public String admin_name;
    public String group_name;

    public String attestation_admin_idstr;

    public String getAttestation_admin_idstr() {
        return attestation_admin_idstr;
    }

    public void setAttestation_admin_idstr(String attestation_admin_idstr) {
        this.attestation_admin_idstr = attestation_admin_idstr;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
