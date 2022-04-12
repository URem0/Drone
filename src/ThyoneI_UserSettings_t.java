public class ThyoneI_UserSettings_t {
    byte ThyoneI_USERSETTING_INDEX_SERIAL_NUMBER               = 0x01;
    byte ThyoneI_USERSETTING_INDEX_FW_VERSION                  = 0x02;
    byte ThyoneI_USERSETTING_INDEX_UART_CONFIG                 = 0x04;
    byte ThyoneI_USERSETTING_INDEX_RF_CHANNEL                  = 0x07;
    byte ThyoneI_USERSETTING_INDEX_ENCRYPTION_MODE             = 0x08;
    byte ThyoneI_USERSETTING_INDEX_RF_PROFILE                  = 0x09;
    byte ThyoneI_USERSETTING_INDEX_RF_NUM_RETRIES              = 0x0A;
    byte ThyoneI_USERSETTING_INDEX_RF_TX_POWER                 = 0x0B;
    byte ThyoneI_USERSETTING_INDEX_RF_RP_NUM_SLOTS             = 0x0C;
    byte ThyoneI_USERSETTING_INDEX_MAC_SOURCE_ADDRESS          = 0x10;
    byte ThyoneI_USERSETTING_INDEX_MAC_DESTINATION_ADDRESS     = 0x11;
    byte ThyoneI_USERSETTING_INDEX_MAC_GROUP_ID                = 0x12;
    byte ThyoneI_USERSETTING_INDEX_MAC_ENCRYPTION_KEY          = 0x14;
    byte ThyoneI_USERSETTING_INDEX_MAC_TLL                     = 0x15;
    byte ThyoneI_USERSETTING_INDEX_CCA_MODE                    = 0x16;
    byte ThyoneI_USERSETTING_INDEX_CCA_THRESHOLD               = 0x17;
    byte ThyoneI_USERSETTING_INDEX_REMOTE_GPIO_CONFIG          = 0x18;
    byte ThyoneI_USERSETTING_INDEX_MODULE_MODE                 = 0x20;

    public byte value;

    public ThyoneI_UserSettings_t(){
        value = ThyoneI_USERSETTING_INDEX_SERIAL_NUMBER;
    }
}
