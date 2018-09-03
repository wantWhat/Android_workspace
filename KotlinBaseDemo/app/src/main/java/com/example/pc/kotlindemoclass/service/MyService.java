package com.example.pc.kotlindemoclass.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Arrays;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        sort();
        int[] arr = {10,20,8,9,1,4,2,5,6,0};
        quickSort(arr, 0, arr.length - 1);
        Log.i("demo", "快速排序==" + Arrays.toString(arr));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    class MyBinder extends Binder {
        public MyService getSerice() {
            Log.i("demo", "service info");
            return MyService.this;
        }
    }

    //冒泡排序
    private void sort() {
        int[] arr = {1, 5, 2, 4, 0};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        Log.i("demo", "冒泡排序== arr==" + Arrays.toString(arr));
    }
    //选择排序
    private void sort1() {
        int[] arr = {1, 5, 2, 4, 0};
        for (int i = 0; i < arr.length; i++) {
            Log.i("demo","i==" + i);
            int minIndex = i;
            for (int j = i ; j < arr.length; j++) {
                Log.i("demo","j==" + j);
                if (arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        Log.i(TAG, "选择排序==" + Arrays.toString(arr));
    }

    //快速排序
    /*速排序是对冒泡排序的一种改进。
            *
            * 快速排序的基本思想： 通过一趟排序将要排序的数据分成独立的两部分，其中一部分的所有数据（左边的数据）都比
            * 另外一部分的所有数据（右边的数据）都要小，然后再按此方法对这两部分数据分别进行快速排序， 整个排序过程可以递归进行，以此达到整个数据变成有序的序列。
            *
            * 一趟快速排序： 在要排序的数据中，首先任意选取一个数据（通常选用第一个数）作为关键数（或称为基准数），
            * 然后将所有比它小的数都放在前面（左边），所有比它大的数都放到后面（右边），这个过程称为一趟排序。
            *
            * 一趟快速排序规则: 1）设置两个变量i、j，排序开始的时候：i=0，j=N-1； 2）以第一个数组元素作为关键数据(即基准数)，赋值给key，即
            * key=A[0]； 3）从j开始向前搜索，即由后开始向前搜索（j--），找到第一个小于key的值A[j]，A[i]与A[j]交换；
            * 4）从i开始向后搜索，即由前开始向后搜索（i++），找到第一个大于key的A[i]，A[i]与A[j]交换； 5）重复执行第3、4步，直到 i=j；
            * 6）到此找到基准点的下标，作为分治下标； 7）重复1-6步骤递归排序前半部分； 8）重复1-6步骤递归排序后半部分。
            * */
    private void quickSort(int [] arr, int minIndex, int maxIndex) {
        if (minIndex >= maxIndex || maxIndex <= 0) return;
        int i = minIndex;
        int j = maxIndex;
        int key = arr[minIndex];//基准元素
        while (true) {
            //从后往前走
            while (j > i) {
                if (arr[j] < key) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    break;
                } else {
                    j --;
                }
            }
            while (j > i) {
                if (arr[i] > key) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    break;
                } else {
                    i++;
                }
            }
            if (i == j) {
                break;
            }

        }
        quickSort(arr, minIndex, i -1);
        quickSort(arr, i + 1, maxIndex);
    }
}
