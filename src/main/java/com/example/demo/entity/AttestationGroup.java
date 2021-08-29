package com.example.demo.entity;


public class AttestationGroup {

    public int attestation_group_id;
    public String group_name;
    public String attestation_id;
    public String created_at;
    public String updated_at;

    public String attestation_group_idstr;

    public String getAttestation_group_idstr() {
        return attestation_group_idstr;
    }

    public void setAttestation_group_idstr(String attestation_group_idstr) {
        this.attestation_group_idstr = attestation_group_idstr;
    }

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
