package com.desktopnotifier;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import android.provider.ContactsContract.PhoneLookup;


public class ProcessCall extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            System.out.println(">>>>"+incomingNumber);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String contactName = getDisplayNameNumber(context,incomingNumber);
                if(contactName!=null){
                    System.out.println(">>"+contactName);
                    sendingMessage(contactName);
                    Toast.makeText(context,"Incoming call from - "+contactName,Toast.LENGTH_LONG).show();
                }else if(incomingNumber!=null){
                    System.out.println(">>"+incomingNumber);
                    sendingMessage(incomingNumber);
                    Toast.makeText(context,"Incoming call from - "+incomingNumber,Toast.LENGTH_LONG).show();
                }

            }else{
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getDisplayNameNumber(Context context,String phoneNumber){
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));
        Cursor cursor =  cr.query(uri,new String[]{PhoneLookup.DISPLAY_NAME},null,null,null);

        if(cursor==null){
            return null;
        }
        String contactName = null;
        if(cursor.moveToFirst()){
            contactName = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));

        }
        if(cursor!=null && !cursor.isClosed()){
            cursor.close();
        }
        return contactName;
    }

    public void sendingMessage(String value){

        /*sqs.setRegion(Region.getRegion("ap-south-1"));
        SendMessageRequest req = new SendMessageRequest("https://sqs.ap-south-1.amazonaws.com/780075415211/GolangQ",value);
        sqs.sendMessageAsync(req, new AsyncHandler<SendMessageRequest, SendMessageResult>() {
            @Override
            public void onError(Exception exception) {
                System.out.println("Exception occured: "+exception.getMessage());
            }

            @Override
            public void onSuccess(SendMessageRequest request, SendMessageResult sendMessageResult) {

                System.out.println("Message Sent: "+sendMessageResult.getMessageId());
            }
        });
 */
        System.out.println("Ended");

    }
}
