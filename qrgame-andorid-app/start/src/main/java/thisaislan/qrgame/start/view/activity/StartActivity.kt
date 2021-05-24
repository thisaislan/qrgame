package thisaislan.qrgame.start.view.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import thisaislan.qrgame.base.view.activity.BaseActivity
import thisaislan.qrgame.base.extesion.sharedPreferences
import thisaislan.qrgame.base.extesion.hasPreviousDenialCameraPermission
import thisaislan.qrgame.base.extesion.isCameraPermissionGranted
import thisaislan.qrgame.start.R

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        navigateTo(getFirstView())
    }

    private fun getFirstView() =
        if (isCameraPermissionGranted()) {
            R.id.welcomeFragment
        } else {
            if (sharedPreferences.hasPreviousDenialCameraPermission()) {
                R.id.needPermissionFragment
            } else {
                R.id.firstStepFragment
            }
        }

    private fun navigateTo(destination: Int) =
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).run {
            val navGraph = navController.navInflater.inflate(R.navigation.nav_start)
            navGraph.startDestination = destination
            navController.graph = navGraph
        }

}