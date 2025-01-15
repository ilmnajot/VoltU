package uz.ilmnajot.voltu.service;

public interface SmsService {
    Boolean sendSmsMessage(String phoneNumber, String text);
}
