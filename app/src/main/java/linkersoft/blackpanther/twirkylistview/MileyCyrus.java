package linkersoft.blackpanther.twirkylistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import linkersoft.blackpanther.trump.TrumpyRecycler;
import linkersoft.blackpanther.twirky.TwerkyListView;

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
    public static class TwerkingOffAdapter extends  TwerkyListView.TwerkAdapter{

        private static final int TWERK_COLORS[]={  0xFFA8A8A8,0xFFBDCBB7,0xFFA3BDBF,0xFFF3F2E6,0xFFBECEC2,0xFFF4E8F7,0xFFC3D9D9, 0xFF4E4F4F,0xFFFAC8CA,0xFFEAC5B5,0xFFAEE8D5,0xFFE3DDC1,0xFFD6EFFF,0xFFF1F1CD,0xFFC7C7CF,0xFFF1DCCE };
        ArrayList<String> data;
        private LayoutInflater inflater;
        private int LayoutResId;

        public TwerkingOffAdapter(Context context, int LayResId, ArrayList<String> data){
            this.data=data;
            inflater =LayoutInflater.from(context);
            this.LayoutResId=LayResId;
        }

        @Override
        public int getItemCount(){
            return (data ==null)?0: data.size();
        }

        @Override
        public Twerkie onCreateViewHolder(ViewGroup parent, int ViewType) {
            View row= inflater.inflate(LayoutResId,parent,false);
            return new Twerkie(row);
        }

        @Override
        public void onBindViewHolder(TwerkHolder twerkholder, int position){
            Twerkie twerkie=(Twerkie)twerkholder;
            twerkie.twerkparent.setBackgroundColor(TWERK_COLORS[getRandom(0,TWERK_COLORS.length-1)]);
            twerkie.twerktext.setText("item "+position);
        }

        public class Twerkie extends  TwerkHolder {

            TextView twerktext;
            View twerkparent;
            private Twerkie(View v){
                super(v);
                twerkparent= v.findViewById(R.id.twerkparent);
                twerktext= v.findViewById(R.id.twerktext);
            }
        }
        private static int getRandom(int min, int max) {
            Random rand = new Random();
            return rand.nextInt((max - min) + 1) + min;
        }

    }

}
