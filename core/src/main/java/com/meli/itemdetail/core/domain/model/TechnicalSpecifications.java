package com.meli.itemdetail.core.domain.model;

public class TechnicalSpecifications {
    private String screen;
    private String internalMemory;
    private String ramMemory;
    private String rearCamera;
    private String frontCamera;
    private String unlockMethod;
    private Boolean nfc;

    public TechnicalSpecifications() {}

    public TechnicalSpecifications(String screen, String internalMemory, String ramMemory, String rearCamera,
                                   String frontCamera, String unlockMethod, Boolean nfc) {
        this.screen = screen;
        this.internalMemory = internalMemory;
        this.ramMemory = ramMemory;
        this.rearCamera = rearCamera;
        this.frontCamera = frontCamera;
        this.unlockMethod = unlockMethod;
        this.nfc = nfc;
    }

    public String getScreen() { return screen; }
    public void setScreen(String screen) { this.screen = screen; }

    public String getInternalMemory() { return internalMemory; }
    public void setInternalMemory(String internalMemory) { this.internalMemory = internalMemory; }

    public String getRamMemory() { return ramMemory; }
    public void setRamMemory(String ramMemory) { this.ramMemory = ramMemory; }

    public String getRearCamera() { return rearCamera; }
    public void setRearCamera(String rearCamera) { this.rearCamera = rearCamera; }

    public String getFrontCamera() { return frontCamera; }
    public void setFrontCamera(String frontCamera) { this.frontCamera = frontCamera; }

    public String getUnlockMethod() { return unlockMethod; }
    public void setUnlockMethod(String unlockMethod) { this.unlockMethod = unlockMethod; }

    public Boolean getNfc() { return nfc; }
    public void setNfc(Boolean nfc) { this.nfc = nfc; }
}
