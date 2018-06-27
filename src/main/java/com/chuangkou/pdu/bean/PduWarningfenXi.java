package com.chuangkou.pdu.bean;

public class PduWarningfenXi {


    /**
     * power : 0
     * overvoltage : 0
     * undervoltage : 0
     * overcurrent : 0
     * open_circuit : 0
     * electric_leakage : 0
     * unsolved : 0
     * in_progress : 0
     * resolved : 0
     * ignored : 0
     */

    private int power;
    private int overvoltage;
    private int undervoltage;
    private int overcurrent;
    private int open_circuit;
    private int electric_leakage;
    private int unsolved;
    private int in_progress;
    private int resolved;
    private int ignored;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getOvervoltage() {
        return overvoltage;
    }

    public void setOvervoltage(int overvoltage) {
        this.overvoltage = overvoltage;
    }

    public int getUndervoltage() {
        return undervoltage;
    }

    public void setUndervoltage(int undervoltage) {
        this.undervoltage = undervoltage;
    }

    public int getOvercurrent() {
        return overcurrent;
    }

    public void setOvercurrent(int overcurrent) {
        this.overcurrent = overcurrent;
    }

    public int getOpen_circuit() {
        return open_circuit;
    }

    public void setOpen_circuit(int open_circuit) {
        this.open_circuit = open_circuit;
    }

    public int getElectric_leakage() {
        return electric_leakage;
    }

    public void setElectric_leakage(int electric_leakage) {
        this.electric_leakage = electric_leakage;
    }

    public int getUnsolved() {
        return unsolved;
    }

    public void setUnsolved(int unsolved) {
        this.unsolved = unsolved;
    }

    public int getIn_progress() {
        return in_progress;
    }

    public void setIn_progress(int in_progress) {
        this.in_progress = in_progress;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public int getIgnored() {
        return ignored;
    }

    public void setIgnored(int ignored) {
        this.ignored = ignored;
    }
}
