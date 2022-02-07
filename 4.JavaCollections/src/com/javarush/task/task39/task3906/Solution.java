package com.javarush.task.task39.task3906;

/* 
Интерфейсы нас спасут!
*/

public class Solution {
    public static void main(String[] args) {
        final SecuritySystem securitySystem = new SecuritySystem();
        final LightBulb lightBulb = new LightBulb();
        final ElectricPowerSwitch electricPowerSwitch = new ElectricPowerSwitch(securitySystem);

        electricPowerSwitch.press();
        electricPowerSwitch.press();
    }
}
