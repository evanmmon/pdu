package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.PduClock;
import com.chuangkou.pdu.entity.PduWarningSet;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.StringUtil;

import java.net.Socket;

/**
 * @Author:
 * @Description:
 * @Date:Created in 16:24 2018/5/18
 */
public class PduClockSetThread implements Runnable {

    Socket socket = null;
    PduClock pduClock = null;
    String machineId = null;

    public PduClockSetThread(Socket socket, PduClock pduClock, String machineId) {
        this.socket = socket;
        this.pduClock = pduClock;
        this.machineId = machineId;
    }


    @Override
    public void run() {
        int y = Integer.parseInt("33", 16);
        //判断如果是开启的定时任务就发送报文设置
        String state = pduClock.getResultstate();
        String action = pduClock.getAction();
        int hour = Integer.valueOf(pduClock.getClocktime().substring(0,2));
        int minute = Integer.valueOf(pduClock.getClocktime().substring(3,5));
        String hourhex = StringUtil.addZeroForNum(Integer.toHexString(hour + y), 2);
        String minutehex = StringUtil.addZeroForNum(Integer.toHexString(minute + y), 2);
        String second = StringUtil.addZeroForNum(Integer.toHexString(0 + y), 2);

        String timeStr = second + minutehex + hourhex;

        if (state.equals("1")) {

            MessageDLT messageDLT = new MessageDLT();
            if (action.equals("0")) {//如果是拉闸断电
                messageDLT.setMachineAddress(machineId);
                messageDLT.setDataTab("04FF0404");
                messageDLT.setControl("14");
                messageDLT.setPassword(BaseController.DLTpassword);
                messageDLT.setAuth(BaseController.DLTcontrol);
                messageDLT.setDataStr(timeStr);

                //发送拉闸的
                String datahex = MessageStringDLTUtils.messageToHex(messageDLT, "write");
            }

            if(action.equals("1")){
                messageDLT.setMachineAddress(machineId);
                messageDLT.setDataTab("04FF0404");
                messageDLT.setControl("14");
                messageDLT.setPassword(BaseController.DLTpassword);
                messageDLT.setAuth(BaseController.DLTcontrol);
                messageDLT.setDataStr(timeStr);

                //发送合闸的
                String datahex = MessageStringDLTUtils.messageToHex(messageDLT, "write");
            }
        }


        //搜索设备ID的 所有状态state为1启动，并且动作类型action为1开启设备的定时任务，按时间逆序并排序

        //搜索设备ID的 所有状态state为1启动，并且动作类型action为0关闭设备的定时任务，按时间逆序并排序


    }

}
