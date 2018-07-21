package testing;

import java.util.ArrayList;
import java.util.Random;

import com.example.utilisateur.qrthing.models.Timelog;

public class TestServer {

    public static void main(String[] args) {
        int nRunners = 20;
        int nStations = 5;

        ArrayList<Integer> active = new ArrayList<Integer>();
        for(int i = 0; i<nRunners; i++) {
            active.add(i);
        }
        Timelog log;
        Random r = new Random();
        while(active.size()>0) {
            try {
                id = active.get(r.nextInt(active.size()));
                curr =
                log = new Timelog(id, curr.station()+1,
                        System.currentTimeMillis());
                if(curr.update(log)+1 == nStations) {
                    active.remove(Integer.valueOf(id));
                }
                lb.log(log);
                System.out.println(lb);
                System.out.println("\n\n\n\n");
                Thread.sleep(40);
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        }
    }

}
