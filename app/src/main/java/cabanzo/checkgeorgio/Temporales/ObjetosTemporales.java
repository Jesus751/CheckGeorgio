package cabanzo.checkgeorgio.Temporales;

import android.content.Context;
import android.content.SharedPreferences;

public class ObjetosTemporales {
    SharedPreferences misShareServer;
    SharedPreferences misSharedUser;
    SharedPreferences misShareLista;

    public ObjetosTemporales(Context context) {
        misShareServer = context.getSharedPreferences("Server", Context.MODE_PRIVATE);
        misSharedUser = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        misShareLista = context.getSharedPreferences("Lista", Context.MODE_PRIVATE);

    }
    public ObjetosTemporales() { }

    public void GuardarItemps(final String id, final String nombre, final String tel, final String pas, final String tipo){
        SharedPreferences.Editor editor = misSharedUser.edit();
        editor.putString("User-Id",id);
        editor.putString("User-Name",nombre);
        editor.putString("User-Tel",tel);
        editor.putString("User-Pas",pas);
        editor.putString("User-Tipo",tipo);
        editor.commit();
    }

    public String ObtieneUserID(){
        String getUserID;
        getUserID = misSharedUser.getString("User-Id","");
        return getUserID;
    }
}
