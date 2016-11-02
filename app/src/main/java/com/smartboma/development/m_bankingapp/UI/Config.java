package com.smartboma.development.m_bankingapp.UI;

/**
 * Created by Mwatha on 31/10/2016.
 */

public class Config {
    public static final String REGISTER_URL = "http://10.0.2.2/eclectics/register.php";

    //Keys for email and password as defined in our $_POST['key'] in register.php
    public static final String RECISTER_FNAME = "fname";
    public static final String RECISTER_LNAME = "lname";
    public static final String REGISTER_PIN = "pin";
    public static final String REGISTER_ACC_NUM = "acc_num";


    public static final String LOGIN_URL = "http://10.0.2.2/eclectics/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String ACC_NUMBER = "acc_num";
    public static final String ACC_PIN = "pin";


    public static final String DEPO_URL = "http://10.0.2.2/eclectics/depourl.php";
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String ACC_DNUMBER = "account_number";
    public static final String AMOUNT = "amount";

    public static final String CHANGEPIN_URL = "http://10.0.2.2/eclectics/changepin.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String ACC_CNUMBER = "acc_num";
    public static  final String ACC_NPIN="pin";

    public static final String STOPCHECK_URL = "http://10.0.2.2/eclectics/stopcheque.php";
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String ACC_MNUMBER = "account_number";
    public static  final String ACC_CHECKPLACED="cheque_placed";
    public static  final String ACC_CHECKNUMBER="cheque_number";

    public static final String CHEQUE_PLACEMENTURL = "http://10.0.2.2/eclectics/chequeurl.php";
    public static final String CHEQUE_NUMBER = "cheque_number";
    public static final String CHEQUE_ACC_NUMBER = "account_number";
    public static final String CHEQUE_PLACED = "cheque_placed";

}
