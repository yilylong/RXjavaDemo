package com.zhl.rx.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhl.rx.R;
import com.zhl.rx.bean.Baozi;
import com.zhl.rx.bean.Car;
import com.zhl.rx.bean.EventMessage1;
import com.zhl.rx.bean.EventMessage2;
import com.zhl.rx.bean.Gender;
import com.zhl.rx.bean.Person;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {
    Subscriber<Person> subscriber;
    TextView tst ;
    ImageView imageResult;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tst = (TextView) findViewById(R.id.toast);
        imageResult = (ImageView) findViewById(R.id.imageResult);
        Button doA = (Button) findViewById(R.id.doA);
        doA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doA();
            }
        });
        Button doB = (Button) findViewById(R.id.doB);
        doB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doB();
            }
        });
        Button doC = (Button) findViewById(R.id.doC);
        doC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doC();
            }
        });
        Button doD = (Button) findViewById(R.id.doD);
        doD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doD();
            }
        });
        Button doE = (Button) findViewById(R.id.doE);
        doE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doE();
            }
        });
        Button doF = (Button) findViewById(R.id.doF);
        doF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doF();
            }
        });
        Button doG = (Button) findViewById(R.id.doG);
        doG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doG();
            }
        });
        Button doH = (Button) findViewById(R.id.doH);
        doH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doH();
            }
        });
        Button doI = (Button) findViewById(R.id.doI);
        doI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doI();
            }
        });
        Button doJ = (Button) findViewById(R.id.doJ);
        doJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJ();
            }
        });
        Button doK = (Button) findViewById(R.id.doK);
        doK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doK();
            }
        });
        Button doL = (Button) findViewById(R.id.doL);
        doL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doL();
            }
        });
        Button doM = (Button) findViewById(R.id.doM);
        doM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doM();
            }
        });
        Button doN = (Button) findViewById(R.id.doN);
        doN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doN();
            }
        });
        Button doO = (Button) findViewById(R.id.doO);
        doO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doO();
            }
        });
        Button doP = (Button) findViewById(R.id.doP);
        doP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doP();
            }
        });
        Button doR = (Button) findViewById(R.id.doR);
        doR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doR();
            }
        });
        Button doS = (Button) findViewById(R.id.doS);
        doS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EmojiTestActivity.class);
                startActivity(intent);
            }
        });
        Button doT = (Button) findViewById(R.id.doT);
        doT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QRcodeImageShareActivity.class);
                startActivity(intent);
            }
        });
        Button doU = (Button) findViewById(R.id.doU);
        doU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomProgressBarActivity.class);
                startActivity(intent);
            }
        });
        Button doV = (Button) findViewById(R.id.doV);
        doV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EventBusTest.class);
                startActivity(intent);
            }
        });
        Button doW = (Button) findViewById(R.id.doW);
        doW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomRefreshHeaderActivity.class);
                startActivity(intent);
            }
        });
        Button doX = (Button) findViewById(R.id.doX);
        doX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondaryScreenTest.class);
                startActivity(intent);
            }
        });
        Button doY = (Button) findViewById(R.id.doY);
        doY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doY();
            }
        });
        Button doZ = (Button) findViewById(R.id.doZ);
        doZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doZ();
            }
        });

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandleEventMessage1(EventMessage1 message){
        Toast.makeText(this,message.getMessage(),0).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandleEventMessage2(EventMessage2 message){
        Toast.makeText(this,"收到消息代码："+message.getCode()+"-"+message.getMessage(),0).show();
    }
    @Subscribe()
    public void onHandleBaoziMessage(Baozi baozi){
        Toast.makeText(this,"收到EventBus消息代码"+baozi.toString(),0).show();
    }

    private void doR() {
        Intent intent = new Intent(this,CoordinatorLayoutTest.class);
        startActivity(intent);
    }
    private void doY() {
        Intent intent = new Intent(this,FileDirTestActivity.class);
        startActivity(intent);
    }
    private void doZ() {
        Intent intent = new Intent(this,AliJavaTest.class);
        startActivity(intent);
    }


    private void doA(){
        Observer<String> overser = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        };
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("this is first rxjava call");
            }
        });

        Observable<String> observable1 = Observable.just("this","is","just");// 依次发送
        String[] words = new String[]{"this","is","from","iterable"};
        Observable<String> observable2 = Observable.from(words);// 从数组或iterable拆分对象发送
//        observable.subscribe(overser);// 订阅
//        observable1.subscribe(overser);// 订阅
        observable2.subscribe(overser);// 订阅
    }

    private void doB(){
        subscriber = new Subscriber<Person>() {
            // 相比Observer多出来的方法，默认空实现，可以用来做一些时间发出前的一些准备工作。
            // 但是注意它的线程是在subscriber事件发出的那个线程，如果需要做UI显示最好使用doOnSubscribe()方法
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Person p) {
                Toast.makeText(MainActivity.this,p.getName()+": "+p.getGender()+"-年龄："+p.getAge(),Toast.LENGTH_SHORT).show();
            }
        };
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setGender(Gender.MEAIL);
        Observable<Person> observable = Observable.just(person);
        observable.subscribeOn(Schedulers.newThread());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(subscriber);
    }

    private void doC() {
        Action1<Person> onNext = new Action1<Person>() {
            @Override
            public void call(Person p) {
                tst.setText(p.getName());
            }
        };
        Action1<Throwable> onError = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Toast.makeText(MainActivity.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };
        Action0 onCompleate = new Action0() {
            @Override
            public void call() {
                Toast.makeText(MainActivity.this,"不完整定义调用完成",Toast.LENGTH_SHORT).show();
            }
        };

        Observable<Person> observable = Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                person = new Person("李四",22);
                person.setGender(Gender.MEAIL);
                ArrayList<Car> cars = new ArrayList<Car>();
                cars.add(new Car("奔驰","S200"));
                cars.add(new Car("宝马","X5"));
                person.setCars(cars);
                subscriber.onNext(person);
                subscriber.onCompleted();
            }
        });
        observable.subscribe(onNext,onError,onCompleate);
    }


    private void doD() {

        Observable.just("android.jpg")
                .map(new Func1<String, Bitmap>() {//.map()转换

                    @Override
                    public Bitmap call(String name) {
                        return getBitmapFromAssets(name);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap s) {
                        imageResult.setImageBitmap(s);
                    }
                });


    }

    private void doE() {

        if(person==null){
            doC();
        }

        Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                subscriber.onNext(person);
            }
        }).flatMap(new Func1<Person, Observable<Car>>() {
            @Override
            public Observable<Car> call(Person person) {
                return Observable.from(person.getCars());
            }
        }).subscribe(new Action1<Car>() {
            @Override
            public void call(Car car) {
                tst.setText(car.getName()+"-"+car.getModel());
            }
        });

    }

    private void doF() {
        Intent intent = new Intent(this,MovieActivity.class);
        startActivity(intent);
    }

    private void doG() {
        Intent intent = new Intent(this,RxJava2XActivity.class);
        startActivity(intent);
    }
    private void doH() {
        Intent intent = new Intent(this,GlideActivity.class);
        startActivity(intent);
    }
    private void doI() {
        Intent intent = new Intent(this,CustomViewGroupActivity.class);
        startActivity(intent);
    }
    private void doJ() {
        Intent intent = new Intent(this,KotlinTest.class);
        startActivity(intent);
    }
    private void doK() {
        Intent intent = new Intent(this,CaptureImageActivity.class);
        startActivity(intent);
    }
    private void doL() {
        Intent intent = new Intent(this,Lock_test.class);
        startActivity(intent);
    }
    private void doM() {
        Intent intent = new Intent(this,CustomViewTestActivity.class);
        startActivity(intent);
    }
    private void doN() {
        Intent intent = new Intent(this,Dagger2Activity.class);
        startActivity(intent);
    }
    private void doO() {
        Intent intent = new Intent(this,CaptureViewActivity.class);
        startActivity(intent);
    }
    private void doP() {
        Intent intent = new Intent(this,FloatImageViewTest.class);
        startActivity(intent);
    }
    private Bitmap getBitmapFromAssets(String name) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = getAssets().open(name);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        if(subscriber!=null){
            subscriber.unsubscribe();
        }
    }
}
