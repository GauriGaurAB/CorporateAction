package com.sdt.corporateaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "GLOBAL_PNL_TEMP")
@Getter @Setter
public class GlobalDetailPnlTmp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", nullable = false)
    private Long id;
    @Column(name="COMPANY_CODE")
    private String companyCode;
    @Column(name="BRATE")
    private String bRate;
    @Column(name="BRATE_ACK")
    private String bRateAck;
    @Column(name="SQTY")
    private String sQty;
    @Column(name="SRATE")
    private String sRate;
    @Column(name="SRATE_ACK")
    private String sRateAck;
    @Column(name="NETQTY")
    private String netQty;
    @Column(name="NRATE")
    private String nRate;
    @Column(name="NRATE_ACK")
    private String nRateAck;
    @Column(name="NETAMT")
    private String netAmt;
    @Column(name="NETAMT_ACK")
    private String netAmtAck;
    @Column(name="CLIENT_ID")
    private String clientId;
    @Column(name="CLIENT_NAME")
    private String clientName;
    @Column(name="BRANCH_CODE")
    private String branchCode;
    @Column(name="FAMILY_GROUP")
    private String familyGroup;
    @Column(name="BRANCH_CODE1")
    private String branchCode1;
    @Column(name="MAIN_BRANCH_CODE")
    private String mainBranchCode;
    @Column(name="CLIENT_ID_FAX")
    private String clientIdFax;
    @Column(name="CLIENT_ID_MAIL")
    private String clientIdMail;
    @Column(name="BRANCH_CODE_FAX")
    private String branchCodeFax;
    @Column(name="BRANCH_CODE_MAIL")
    private String branchCodeMail;
    @Column(name="BRANCH_NAME")
    private String branchName;
    @Column(name="SCRIP_NAME")
    private String scripName;
    @Column(name="FAMILY_GROUP_NAME")
    private String familyGroupName;
    @Column(name="FAMILY_GROUP_MAIL")
    private String familyGroupMail;
    @Column(name="FAMILY_GROUP_FAX")
    private String familyGroupFax;
    @Column(name="NARRATION")
    private String narration;
    @Column(name="SR")
    private String sr;
    @Column(name="MARKET")
    private String market;
    @Column(name="EXCHANGE")
    private String exchange;
    @Column(name="SCRIP_SYMBOL")
    private String scripSymbol;
    @Column(name="EXPIRY_DATE")
    private String expiryDate;
    @Column(name="TRADE_DATE")
    private String tradeDate;
    @Column(name="TRADE_DATEDT")
    private String tradeDateDt;
    @Column(name="BQTY")
    private String bQty;
    @Column(name="BAMT")
    private String bAmt;
    @Column(name="BAMT_ACK")
    private String bAmtAck;
    @Column(name="SAMT")
    private String sAmt;
    @Column(name="SAMT_ACK")
    private String sAmtAck;
    @Column(name="created_at")
    private String createdAt;
}
