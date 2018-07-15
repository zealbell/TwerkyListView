[![Project Status: Active - Initial development has started, temporary release; work hasn't been stopped ](http://www.repostatus.org/badges/0.1.0/active.svg)](http://www.repostatus.org/#active)

TwerkyListView
=============
A beautiful RecyclerView which list items and animates similar to the peristaltic motion(twerking) of earthworms.(Inspiration
for this project wasn't completely drawn imagining or studying earthworms move neither does the author eat or rare earthworms)
also an *IllegalTwerkingException()* is thrown if Adapter does not extend *TwerkAdapter*

> *public-methods*

```java
      public void setTwerkInterpolator(Interpolator twerkinpole) // decide twerking motion interpolation
      public void setTwerking(boolean twerking)// enable/cancel twerking
      public void setTwerkingOffTime(int twerkOfftime)// reset the time it takes to complete twerk
```

> *public-static-class*

```java
  class TwerkAdapter{...}
```
## Appearance

![Demo](shots/appearance.gif)

## Quick Start

> Gradle

```xml
   dependencies {
        implementation 'com.github.54LiNKeR:TrumpyRecyclerView:1.trump.2'
        implementation 'com.github.54LiNKeR:TwerkyListView:1.twerk.0'
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
            app:ShrinkStretchSpan="6" // number list items to contract and expand when twerking
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
 - TwerkingOffAdapter demo-source https://gist...

> *project is still under development*