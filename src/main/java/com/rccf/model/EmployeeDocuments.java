package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "employee_documents", schema = "rccf", catalog = "")
public class EmployeeDocuments {
    private int eid;
    private String idcardPositive;
    private String idcardNegative;
    private String education;
    private String jobTitle;
    private String picture;
    private String leavingProof;
    private String criditReport;
    private String nocrimeReport;
    private String examination;
    private String remarks;
    private int id;

    @Id
    @Column(name = "eid")
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "idcard_positive")
    public String getIdcardPositive() {
        return idcardPositive;
    }

    public void setIdcardPositive(String idcardPositive) {
        this.idcardPositive = idcardPositive;
    }

    @Basic
    @Column(name = "idcard_negative")
    public String getIdcardNegative() {
        return idcardNegative;
    }

    public void setIdcardNegative(String idcardNegative) {
        this.idcardNegative = idcardNegative;
    }

    @Basic
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "job_title")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "leaving_proof")
    public String getLeavingProof() {
        return leavingProof;
    }

    public void setLeavingProof(String leavingProof) {
        this.leavingProof = leavingProof;
    }

    @Basic
    @Column(name = "cridit_report")
    public String getCriditReport() {
        return criditReport;
    }

    public void setCriditReport(String criditReport) {
        this.criditReport = criditReport;
    }

    @Basic
    @Column(name = "nocrime_report")
    public String getNocrimeReport() {
        return nocrimeReport;
    }

    public void setNocrimeReport(String nocrimeReport) {
        this.nocrimeReport = nocrimeReport;
    }

    @Basic
    @Column(name = "examination")
    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeDocuments documents = (EmployeeDocuments) o;

        if (eid != documents.eid) return false;
        if (id != documents.id) return false;
        if (idcardPositive != null ? !idcardPositive.equals(documents.idcardPositive) : documents.idcardPositive != null)
            return false;
        if (idcardNegative != null ? !idcardNegative.equals(documents.idcardNegative) : documents.idcardNegative != null)
            return false;
        if (education != null ? !education.equals(documents.education) : documents.education != null) return false;
        if (jobTitle != null ? !jobTitle.equals(documents.jobTitle) : documents.jobTitle != null) return false;
        if (picture != null ? !picture.equals(documents.picture) : documents.picture != null) return false;
        if (leavingProof != null ? !leavingProof.equals(documents.leavingProof) : documents.leavingProof != null)
            return false;
        if (criditReport != null ? !criditReport.equals(documents.criditReport) : documents.criditReport != null)
            return false;
        if (nocrimeReport != null ? !nocrimeReport.equals(documents.nocrimeReport) : documents.nocrimeReport != null)
            return false;
        if (examination != null ? !examination.equals(documents.examination) : documents.examination != null)
            return false;
        if (remarks != null ? !remarks.equals(documents.remarks) : documents.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (idcardPositive != null ? idcardPositive.hashCode() : 0);
        result = 31 * result + (idcardNegative != null ? idcardNegative.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (leavingProof != null ? leavingProof.hashCode() : 0);
        result = 31 * result + (criditReport != null ? criditReport.hashCode() : 0);
        result = 31 * result + (nocrimeReport != null ? nocrimeReport.hashCode() : 0);
        result = 31 * result + (examination != null ? examination.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
