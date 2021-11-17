package com.example.petscoffee.task;

import android.app.Service;
import android.os.AsyncTask;

import com.example.petscoffee.action.Worker;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.file.Archive;
import com.example.petscoffee.listener.WorkListener;

import java.util.ArrayList;

public class WorkTask extends AsyncTask<Integer, Integer, Integer> {
    private WorkListener listener;
    private CoffeeShop coffee;
    private Service service;
    private final static int TYPE_SUCCESS = 0;
    private final static int TYPE_PAUSE = 1;

    public WorkTask(Service service,WorkListener listener) {
        this.listener = listener;
        this.coffee = Archive.load(service);
        this.service = service;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer) {
            case TYPE_SUCCESS:
                float money = 0;
                ArrayList<String> process = new ArrayList<>();
                ArrayList<String> bill = new ArrayList<>();
                for (int i = 0; i < coffee.getPets().size(); i++) {
                    Worker worker = new Worker(service, i);
                    process.add(worker.getProcess());//process字符串，用来存储宠物在营业中的经历
                    bill.add(worker.getBill());//bill存储宠物收入字符串，用于输出到营业结果界面
                    money += worker.getIncome();//该宠物带来的收入
                }
                listener.onSuccess(money, process, bill);
                break;
            case TYPE_PAUSE:
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        listener.onProgress(progress);//调用Listener中的方法
    }

    protected Integer doInBackground(Integer... integers) {
            int progress = 0;
            while (progress < 100) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += 5;
                publishProgress(progress);
            }
        return TYPE_SUCCESS;
    }
}
