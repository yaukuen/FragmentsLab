package group9.tcss450.uw.edu.fragmentslab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, new FirstFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onFragmentInteraction(int color) {
        Log.d("ACTIVITY", "Red: " + Color.red(color) +
                " Green: " + Color.green(color) +
                " Blue: " + Color.blue(color));
        Toast.makeText(MainActivity.this,
                "Red: " + Color.red(color) +
                        " Green: " + Color.green(color) +
                        " Blue: " + Color.blue(color),
                Toast.LENGTH_SHORT).show();

        FlowerFragment flowerFragment;

        flowerFragment = (FlowerFragment) getSupportFragmentManager().
                findFragmentById(R.id.flower);
        if (flowerFragment != null) {
            flowerFragment.updateContent(color);
        } else {
            flowerFragment = new FlowerFragment();
            Bundle args = new Bundle();
            args.putSerializable(flowerFragment.COLOR, color);
            flowerFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, flowerFragment)
                    .addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        }
    }
}
