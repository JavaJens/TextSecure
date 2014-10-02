package org.whispersystems.textsecure.push;

public class AccountAttributes {

  private String  signalingKey;
  private boolean supportsSms;
  private boolean fetchesMessages;
  private int     registrationId;

  public AccountAttributes(String signalingKey, boolean supportsSms, int registrationId) {
    this(signalingKey, supportsSms, registrationId, false);
  }

  public AccountAttributes() {}

  public AccountAttributes(String signalingKey, boolean supportsSms, int registrationId, boolean fetchesMessages) {
    this.signalingKey    = signalingKey;
    this.supportsSms     = supportsSms;
    this.registrationId  = registrationId;
    this.fetchesMessages = fetchesMessages;
  }

  public String getSignalingKey() {
    return signalingKey;
  }

  public boolean isSupportsSms() {
    return supportsSms;
  }
  public boolean isFetchesMessages() {
    return fetchesMessages;
  }

  public int getRegistrationId() {
    return registrationId;
  }
}
