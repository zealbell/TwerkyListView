 <img src="https://github.com/54LiNKeR/54LiNKeR.github.io/blob/master/shots/LiNKeR.png" width="30%">
 
[![Project Status: Active - Initial development has started, temporary release; work hasn't been stopped ](http://www.repostatus.org/badges/0.1.0/active.svg)](http://www.repostatus.org/#active)

TwerkyListView
=============
A beautiful RecyclerView which list items and animates similar to the peristaltic motion(twerking) of earthworms in Africa.(Inspiration
for this project wasn't completely drawn imagining or studying earthworms move neither does the author eat or rear earthworms hence any decision made by the reader to google twerking-African earthworms is at the sole discretion of the reader).
also an __*IllegalTwerkingException()*__ is thrown if Adapter does not extend __*TwerkyListView$TwerkAdapter*__

> *Method Summary*

| Method | Return | What it does | 
|----------|---------|--------|
| setTwerkInterpolators(Interpolator twerkpoleStart,Interpolator twerkpoleEnd) | void |  decides twerking start and end motion interpolation |
| setTwerking(boolean twerking) | void | enables/cancels twerking |
| setTwerkingOffTime(int twerkOfftime) | void | resets the time it takes to complete twerk |

> by default(if __*setTwerkInterpolators(...)*__ is never called)
>    -  `twerkpoleStart` = new FastOutSlowInInterpolator();
>    - `twerkpoleEnd` = new OvershootInterpolator();

> *Class Summary*

| Class | Type | What it does | 
|----------|---------|--------|
| TwerkAdapter | static | Twerks the Adapter |

## Inspiration
> 'Invite me' by Stan Yakusevich @Dribble

![Demo](shots/inspiration.gif)

## Appearance

![Demo](shots/appearance.gif)

## Quick Start

> Gradle

```xml
   dependencies {
        implementation 'com.github.54LiNKeR:TrumpyRecyclerView:1.trump.2'
        implementation 'com.github.54LiNKeR:TwerkyListView:1.twerk.1'
   }
```

> XML

```xml
        <linkersoft.blackpanther.twirky.TwerkyListView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/twerk"
            app:stretchScale="0.5" // expansion scale
            app:shrinkScale="0.5" // contraction scale
            app:ShrinkStretchSpan="6" // number of list items to contract and expand when twerking
            app:twerkStyle="UP"/>
```

> Activity

```java
         public class MileyCyrus extends AppCompatActivity {


             @Override
             protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.twirky);
                 Context context=this;

                 //FAKE-DATA
                 ArrayList<String> data=new ArrayList<>();
                 for (int i = 0; i < 20; i++)data.add(null);
                 //TWERKING-VIEW
                 TwerkyListView twerkList=(TwerkyListView)findViewById(R.id.twerk);
                 //LAYOUT
                 TrumpyRecycler.TrumpyLinearLayoutManager tllm= new TrumpyRecycler.TrumpyLinearLayoutManager(context);
                 tllm.setOrientation(LinearLayoutManager.VERTICAL);
                 twerkList.setLayoutManager(tllm);
                 twerkList.setFlingFactor(TrumpyRecycler.MODERATE_FLING);
                 twerkList.setFrictionFactor(TrumpyRecycler.NO_FRICTION);
                 twerkList.setOverScrollMode(TrumpyRecycler.OVER_SCROLL_NEVER);
                 //ADAPTER
                 TwerkingOffAdapter twerkdapter=new TwerkingOffAdapter(context,R.layout.twerkrow,data);
                 twerkList.setAdapter(twerkdapter);
                 //TWERKING
                 twerkList.setTwerking(true);
             }

             //TWERK-ADAPTER
             public static class TwerkingOffAdapter extends  TwerkyListView.TwerkAdapter{...}
```
 - `adapter-example` [TwerkingOffAdapter](https://gist.github.com/54LiNKeR/e22021d7b8b970d1f99bc2ca0a22f669)
 

 

> *project is still under development*

[![GooglePlay](shots/playstore_badge.png)](http://play.google.com/store/apps/details?id=linkersoft.blackpanther.twirkylistview)


![LiNKeR](https://github.com/54LiNKeR/54LiNKeR.github.io/blob/master/shots/%23Zealbell.png)

