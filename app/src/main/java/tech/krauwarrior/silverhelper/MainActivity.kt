package tech.krauwarrior.silverhelper

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import tech.krauwarrior.silverhelper.databinding.ActivityMainBinding
import tech.krauwarrior.silverhelper.helpers.HCountDown
import tech.krauwarrior.silverhelper.helpers.HReverseTriggerDialog
import tech.krauwarrior.silverhelper.helpers.VCountDown

class MainActivity : AppCompatActivity(), HCountDown.onCountDownListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val DURATION = 3000L
    private val INTERVAL = 1000L

    private lateinit var viewCountDown: VCountDown
    private lateinit var hReverseTriggerDialog: HReverseTriggerDialog
    private lateinit var hCountDown: HCountDown

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewCountDown = findViewById(R.layout.dialog_reverse_trigger)
        hCountDown = HCountDown(DURATION, INTERVAL, this)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        initView()
    }

    fun startCountDown(view: View?) {
        hCountDown.startCountDown()
    }

    fun startDialogCountDown(view: View?) {
        hReverseTriggerDialog = HReverseTriggerDialog(this)
        hReverseTriggerDialog.show()
    }

    private fun initView() {
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "语音操作预留按钮", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onTick(millisUntilFinished: Long) {
        viewCountDown.setProgress(
            DURATION / INTERVAL,
            (millisUntilFinished.toDouble() / DURATION * (DURATION / INTERVAL)).toLong()
        )
    }

    override fun onFinish() {
        viewCountDown.setProgress(DURATION / INTERVAL, 0)
    }
}