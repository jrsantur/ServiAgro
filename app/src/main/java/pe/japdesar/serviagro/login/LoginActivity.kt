package pe.japdesar.serviagro.login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import pe.japdesar.serviagro.R
import pe.japdesar.serviagro.databinding.ActivityLoginBinding
import pe.japdesar.serviagro.login.buildlogic.LoginInjector


class LoginActivity : AppCompatActivity(), ILoginContract.View {
    override fun setObserver(observer: Observer<LoginEvent<LoginResult>>) = event.observeForever(observer)

    override fun startListFeature() = pe.japdesar.serviagro.common.startListFeature(this)
    //var layoutInflater: LayoutInflater = this.GetSystemService(Context.LayoutInflaterService) as LayoutInflater
    val event = MutableLiveData<LoginEvent<LoginResult>>()
    var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //Note: I cal setObserver within the LoginInjector function
        ViewModelProviders.of(this)
            .get(LoginInjector::class.java)
            .buildLoginLogic(this)

        //btn_auth_attempt.setOnClickListener { event.value = LoginEvent.OnAuthButtonClick }
        //imb_toolbar_back.setOnClickListener { event.value = LoginEvent.OnBackClick }
    }


    override fun onResume() {
        super.onResume()
        event.value = LoginEvent.OnStart
    }

    override fun setLoginStatus(text: String) {
        //binding!!.lblLoginStatusDisplay.text = text
    }

    override fun setAuthButton(text: String) {
        //binding!!.btnAuthAttempt.text = text
    }

    override fun showLoopAnimation() {
       /*
        binding!!.imvAntennaAnimation.setImageResource(
            resources.getIdentifier("antenna_loop_fast", "drawable", this.packageName)
        )

        val satelliteLoop = binding!!.imvAntennaAnimation.drawable as AnimationDrawable
        satelliteLoop.start()
        */
    }

    override fun setStatusDrawable(imageURL: String) {
        /*
        binding!!.imvAntennaAnimation.setImageResource(
            resources.getIdentifier(imageURL, "drawable", this.packageName)
        )
         */

    }

    override fun startSignInFlow() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                event.value = LoginEvent.OnGoogleSignInResult(
                    LoginResult(
                        requestCode,
                        account
                    )
                )

            } catch (exception: Exception) {
                event.value = LoginEvent.OnGoogleSignInResult(
                    LoginResult(
                        0,
                        null
                    )
                )
            }
        }
    }
}