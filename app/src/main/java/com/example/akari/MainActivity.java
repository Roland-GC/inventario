package com.example.akari;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);


        //isa
        databaseHelper.addProduct("print A5", "I_01_A5", 12, "5");
        databaseHelper.addProduct("print A4", "I_02_A4", 0, "5");
        databaseHelper.addProduct("print A3", "I_03_A3", 5, "10");
        databaseHelper.addProduct("lenticular A5", "I_04_lentA5", 25, "10");
        databaseHelper.addProduct("lenticular A4", "I_05_lentA4", 21, "15");
        databaseHelper.addProduct("lenticular A3", "I_06_lentA3", 22, "25");
        databaseHelper.addProduct("cuaderno lenticular", "I_07_clentA5", 5, "15");
        databaseHelper.addProduct("cuaderno espiral A6", "I_08_spiralA6", 10, "5");
        databaseHelper.addProduct("cuaderno espiral A5", "I_09_spiralA5", 5, "8");
        databaseHelper.addProduct("cuaderno duro A6", "I_10_hardA6", 50, "8");
        databaseHelper.addProduct("cuaderno duro A5", "I_11_hardA5", 5, "12");
        databaseHelper.addProduct("agenda A6", "I_12_claspA6", 5, "8");
        databaseHelper.addProduct("agenda A5", "I_13_claspA5", 5, "12");
        databaseHelper.addProduct("tapete 30", "I_14_tapete30", 20, "25");
        databaseHelper.addProduct("tapete 35", "I_15_tapete35", 10, "28");
        databaseHelper.addProduct("tapete 40", "I_16_tapete40", 10, "30");
        databaseHelper.addProduct("tapete LED", "I_17_tapLED", 25, "40");
        databaseHelper.addProduct("alfombrilla", "I_18_mpad", 25, "8");
        databaseHelper.addProduct("alfombrilla 3D", "I_19_mpad3D", 9, "25");
        databaseHelper.addProduct("gamuza", "I_20_gamuza", 153, "3");
        databaseHelper.addProduct("estuche", "I_21_cuboid", 79, "10");
        databaseHelper.addProduct("tote bag", "I_22_tote", 25, "15");
        databaseHelper.addProduct("Z tote bag", "I_23_Ztote", 10, "20");
        databaseHelper.addProduct("peluche", "I_24_peluche", 50, "15");
        databaseHelper.addProduct("pin acrílico", "I_25_apin", 32, "3");
        databaseHelper.addProduct("llavero acrílico", "I_26_akey", 125, "7");
        databaseHelper.addProduct("stand 20", "I_27_astand", 1, "20");
        databaseHelper.addProduct("stand doble", "I_28_astandD", 8, "25");
        databaseHelper.addProduct("iman acrílico", "I_29_aiman", 49, "7");
        databaseHelper.addProduct("llavero metal", "I_30_mkey", 20, "8");
        databaseHelper.addProduct("colgante metal", "I_31_mneck", 10, "8");
        databaseHelper.addProduct("pendientes metal", "I_32_mear", 10, "12");
        databaseHelper.addProduct("pin metal", "I_33_mpin", 20, "8");
        databaseHelper.addProduct("chapa", "I_34_chapa", 10, "3");
        databaseHelper.addProduct("phone grip", "I_35_phone", 10, "7");
        databaseHelper.addProduct("pegatinas", "I_36_pegata", 10, "3");
        databaseHelper.addProduct("washi tape", "I_37_washi", 41, "3");
        databaseHelper.addProduct("foil washi tape", "I_38_fwashi", 36, "3");
        databaseHelper.addProduct("glitter washi tape", "I_39_gwashi", 36, "4");
        databaseHelper.addProduct("stamp washi tape", "I_40_swashi", 36, "4");
        databaseHelper.addProduct("aplique LED", "I_41_apliqueLED", 1, "35");
        databaseHelper.addProduct("lampara LED", "I_42_lampLED", 10, "25");
        databaseHelper.addProduct("gafas LED", "I_43_gafasLED", 10, "15");
        databaseHelper.addProduct("gafas hada", "I_44_gafashada", 20, "15");
        databaseHelper.addProduct("oferta gafas", "I_45_ofergafas", 20, "20");
        databaseHelper.addProduct("oferta caja washi", "I_46_oferwashi", 26, "8");
        databaseHelper.addProduct("oferta chapas", "I_47_oferchapa", 20, "5");
        databaseHelper.addProduct("oferta mpins", "I_48_ofermpin", 20, "12");
        databaseHelper.addProduct("oferta llaveros", "I_49_ofermkey", 20, "12");
        databaseHelper.addProduct("oferta acrílicos", "I_50_oferakey", 20, "10");
        databaseHelper.addProduct("oferta set frozen", "I_51_setfrozen", 5, "20");
        databaseHelper.addProduct("oferta set Z tote", "I_52_oferZtote", 10, "30");
        databaseHelper.addProduct("oferta set tote", "I_53_ofertote", 10, "25");
        databaseHelper.addProduct("oferta caja cole", "I_54_ofercole", 6, "20");
        databaseHelper.addProduct("oferta tapete LED", "I_55_oferlampLED", 10, "70");
        databaseHelper.addProduct("oferta tapete 40", "I_56_ofertapete40", 10, "50");
        databaseHelper.addProduct("oferta tapete 35", "I_57_ofertapete35", 10, "45");
        databaseHelper.addProduct("oferta tapete 30", "I_58_ofertapete30", 10, "40");
        databaseHelper.addProduct("oferta alfombrilla", "I_59_ofermpad", 10, "15");
        databaseHelper.addProduct("taza blanca", "I_60_taza", 0, "10");
        databaseHelper.addProduct("taza Luffy", "I_61_luffy", 12, "40");
        databaseHelper.addProduct("taza Ace", "I_62_ace", 10, "40");
        databaseHelper.addProduct("oferta set taza", "I_63_ofertaza", 12, "60");
        databaseHelper.addProduct("oferta taza mágica", "I_64_ofertazam", 7, "5");
        databaseHelper.addProduct("print foil", "I_65_foil", 600, "8");
        databaseHelper.addProduct("cuaderno foil", "I_66_cuadernofoil", 12, "10");
        databaseHelper.addProduct("bolsas sorpresa10", "I_67_bolsa10", 20, "10");
        databaseHelper.addProduct("bolsas sorpresa20", "I_68_bolsa20", 20, "20");
        databaseHelper.addProduct("bolsas sorpresa30", "I_67_bolsa30", 18, "30");

        //patricia
        databaseHelper.addProduct("print A6", "K_01_A6", 109, "4");
        databaseHelper.addProduct("print A5", "K_02_A5", 56, "5");
        databaseHelper.addProduct("print A4", "K_03_A4", 13, "7");
        databaseHelper.addProduct("Pegatina holo. Zodiaco", "K_04_PHolo", 124, "2");
        databaseHelper.addProduct("Pegatina sello", "K_05_Pvinilo", 211, "1");
        databaseHelper.addProduct("Maderas zodiaco", "K_06_MadZod", 43, "8");
        databaseHelper.addProduct("Maderas pintadas", "K_07_Madpint", 25, "10");
        databaseHelper.addProduct("Totabag Forest Souls", "K_08_toteforest", 3, "20");
        databaseHelper.addProduct("Pendientes fanart", "K_09_penfanart", 50, "12");
        databaseHelper.addProduct("Pendientes setas grandes", "K_10_pensetaG", 40, "15");
        databaseHelper.addProduct("Pendientes witchy", "K_11_penwitchy", 75, "10");
        databaseHelper.addProduct("colgantes japoneses", "K_12_Ckitsune", 21, "15");
        databaseHelper.addProduct("pines japoneses", "K_13_pinkitsune", 11, "9");
        databaseHelper.addProduct("Colgantes variados", "K_14_colgantes", 33, "15");
        databaseHelper.addProduct("Colgante ff serpiente", "K_15_ffserpiente", 4, "32");
        databaseHelper.addProduct("Colgantes ff variados", "K_16_ffdemásc", 30, "30");
        databaseHelper.addProduct("Colgante ff noesno", "K_17_ffnoesno", 5, "22");
        databaseHelper.addProduct("Pendientes ff grandes", "K_18_ffpenG", 15, "22");
        databaseHelper.addProduct("Pendientes ff pequeños", "K_19_ffpenP", 17, "20");
        databaseHelper.addProduct("Oferta 3x2 holos.", "K_20_ofertaholos", 50, "4");
        databaseHelper.addProduct("Oferta 3 pegat.sellos", "K_21_ofertasellos", 100, "2.5");
        databaseHelper.addProduct("Oferta 2 maderas zodiaco", "K_22_ofertamadzod", 25, "15");
        databaseHelper.addProduct("Oferta 2 maderas pintadas", "K_23_ofertamadpint", 20, "18");
        databaseHelper.addProduct("Oferta 3 A6", "K_24_oferta3A6", 30, "10");
        databaseHelper.addProduct("Sobre sorpresa", "K_25_Ssorpresa", 30, "7");

        //akari
        databaseHelper.addProduct("AD_Standee", "AKD_std_0000", 1, "18");
        databaseHelper.addProduct("AD_PrintCuadrada", "AKD_cdd_0001", 650, "4");
        databaseHelper.addProduct("AD_PrintDinA5", "AKD_da5_0002", 450, "5");
        databaseHelper.addProduct("AD_Marcapaginas", "AKD_map_0003", 1550, "3.5");
        databaseHelper.addProduct("AD_SobreSorpresa", "AKD_mps_0004", 50, "2");
        databaseHelper.addProduct("AD_Cuaderno", "AKD_cua_0005", 50, "7");
        databaseHelper.addProduct("AD_Pegatina", "AKD_peg_0006", 850, "1");
        databaseHelper.addProduct("AD_Chapa", "AKD_cha_0007", 150, "2");
        databaseHelper.addProduct("AD_Espejos", "AKD_esp_0008", 50, "2");
        databaseHelper.addProduct("AD_Abrebotellas", "AKD_abr_0009", 50, "2");
        databaseHelper.addProduct("AD_Imanes", "AKD_ima_0010", 30, "2");
        databaseHelper.addProduct("AD_Gamuzas", "AKD_gam_0011", 250, "3");
        databaseHelper.addProduct("AD_Peluche", "AKD_pel_0012", 9, "15");
        databaseHelper.addProduct("AD_Pin Acrilico", "AKD_pin_0013", 150, "3.5");
        databaseHelper.addProduct("AD_Pin Madera", "AKD_pin_0014", 15, "3.5");
        databaseHelper.addProduct("AD_Minis", "AKD_min_0015", 50, "3");
        databaseHelper.addProduct("AD_Lllaveros", "AKD_llv_0016", 350, "7");
        databaseHelper.addProduct("AD_PhoneGrips", "AKD_pho_0017", 50, "7");
        databaseHelper.addProduct("AD_Lampara", "AKD_lam_0018", 24, "30");
        databaseHelper.addProduct("AD_OPrintCuadrada", "AKD_Ocd_0019", 100, "10");
        databaseHelper.addProduct("AD_OPrintDinA5", "AKD_Ocd_0020", 100, "12");
        databaseHelper.addProduct("AD_OMarcapaginas", "AKD_Omp_0021", 150, "8");
        databaseHelper.addProduct("AD_OPegatina", "AKD_Opg_0020", 350, "2");

        //cell
        databaseHelper.addProduct("A5", "I_01_A5", 100, "3");
        databaseHelper.addProduct("A4", "I_02_A4", 50, "5");
        databaseHelper.addProduct("A3", "I_03_A3", 50, "10");
        databaseHelper.addProduct("lenticular A5", "I_04_lentA5", 20, "10");
        databaseHelper.addProduct("lenticular A4", "I_05_lentA4", 20, "15");
        databaseHelper.addProduct("lenticular A3", "I_06_lentA3", 20, "25");
        databaseHelper.addProduct("cuaderno lenticular", "I_07_clentA5", 5, "15");
        databaseHelper.addProduct("cuaderno espiral A6", "I_08_spiralA6", 10, "5");
        databaseHelper.addProduct("cuaderno espiral A5", "I_09_spiralA5", 5, "8");
        databaseHelper.addProduct("cuaderno duro A6", "I_10_hardA6", 50, "8");
        databaseHelper.addProduct("cuaderno duro A5", "I_11_hardA5", 5, "12");
        databaseHelper.addProduct("agenda A6", "I_12_claspA6", 5, "8");
        databaseHelper.addProduct("agenda A5", "I_13_claspA5", 5, "12");
        databaseHelper.addProduct("tapete 30", "I_14_tapete30", 20, "25");
        databaseHelper.addProduct("tapete 35", "I_15_tapete35", 10, "28");
        databaseHelper.addProduct("tapete 40", "I_16_tapete40", 10, "30");
        databaseHelper.addProduct("tapete LED", "I_17_tapLED", 25, "40");
        databaseHelper.addProduct("alfombrilla", "I_18_mpad", 10, "8");
        databaseHelper.addProduct("alfombrilla 3D", "I_19_mpad3D", 10, "25");
        databaseHelper.addProduct("gamuza", "I_20_gamuza", 100, "3");
        databaseHelper.addProduct("estuche", "I_21_cuboid", 50, "10");
        databaseHelper.addProduct("tote bag", "I_22_tote", 20, "15");
        databaseHelper.addProduct("Z tote bag", "I_23_Ztote", 20, "20");
        databaseHelper.addProduct("peluche", "I_24_peluche", 50, "15");
        databaseHelper.addProduct("pin acrílico", "I_25_apin", 20, "3");
        databaseHelper.addProduct("llavero acrílico", "I_26_akey", 50, "7");
        databaseHelper.addProduct("stand 20", "I_27_astand", 10, "20");
        databaseHelper.addProduct("stand doble", "I_28_astandD", 10, "25");
        databaseHelper.addProduct("iman acrílico", "I_29_aiman", 10, "7");
        databaseHelper.addProduct("llavero metal", "I_30_mkey", 10, "8");
        databaseHelper.addProduct("colgante metal", "I_31_mneck", 10, "8");
        databaseHelper.addProduct("pendientes metal", "I_32_mear", 10, "12");
        databaseHelper.addProduct("pin metal", "I_33_mpin", 10, "8");
        databaseHelper.addProduct("chapa", "I_34_chapa", 10, "3");
        databaseHelper.addProduct("phone grip", "I_35_phone", 10, "7");
        databaseHelper.addProduct("pegatinas", "I_36_pegata", 10, "3");
        databaseHelper.addProduct("wahi tape", "I_37_washi", 10, "3");
        databaseHelper.addProduct("foil wahi tape", "I_38_fwashi", 10, "3");
        databaseHelper.addProduct("glitter washi tape", "I_39_gwashi", 10, "4");
        databaseHelper.addProduct("stamp washi tape", "I_40_swashi", 10, "4");
        databaseHelper.addProduct("aplique LED", "I_41_apliqueLED", 10, "35");
        databaseHelper.addProduct("lampara LED", "I_42_lampLED", 10, "25");
        databaseHelper.addProduct("gafas LED", "I_43_gafasLED", 10, "15");
        databaseHelper.addProduct("gafas hada", "I_44_gafashada", 10, "15");
        databaseHelper.addProduct("oferta gafas", "I_45_ofergafas", 10, "20");
        databaseHelper.addProduct("oferta caja washi", "I_46_oferwashi", 10, "8");
        databaseHelper.addProduct("oferta chapas", "I_47_oferchapa", 10, "5");
        databaseHelper.addProduct("oferta mpins", "I_48_ofermpin", 10, "12");
        databaseHelper.addProduct("oferta llaveros", "I_49_ofermkey", 10, "12");
        databaseHelper.addProduct("oferta acrílicos", "I_50_oferakey", 10, "10");
        databaseHelper.addProduct("oferta set frozen", "I_51_setfrozen", 10, "20");
        databaseHelper.addProduct("oferta set Z tote", "I_52_oferZtote", 10, "30");
        databaseHelper.addProduct("oferta set tote", "I_53_ofertote", 10, "25");
        databaseHelper.addProduct("oferta caja cole", "I_54_ofercole", 10, "20");
        databaseHelper.addProduct("oferta tapete LED", "I_55_oferlampLED", 10, "70");
        databaseHelper.addProduct("oferta tapete 40", "I_56_ofertapete40", 10, "50");
        databaseHelper.addProduct("oferta tapete 35", "I_57_ofertapete35", 10, "45");
        databaseHelper.addProduct("oferta tapete 30", "I_58_ofertapete30", 10, "40");
        databaseHelper.addProduct("oferta alfombrilla", "I_59_ofermpad", 10, "15");
        databaseHelper.addProduct("taza blanca", "I_59_taza", 8, "10");
        databaseHelper.addProduct("taza Luffy", "I_59_luffy", 12, "40");
        databaseHelper.addProduct("taza Ace", "I_59_ace", 12, "40");
        databaseHelper.addProduct("oferta set taza", "I_59_ofertaza", 12, "60");
        databaseHelper.addProduct("oferta taza mágica", "I_59_ofertazam", 8, "5");
        //blumi
        databaseHelper.addProduct("Cuadernos A5 duro", "ba_01_duroA5", 8, "9");
        databaseHelper.addProduct("Cuaderno A5 extraduro", "ba_02_extraduroA5", 4, "10");
        databaseHelper.addProduct("Cuadernos A6 duro", "ba_03_duroA6", 27, "6");
        databaseHelper.addProduct("Cuaderno A6 extraduro", "ba_04_extraduroA6", 17, "7");
        databaseHelper.addProduct("pegatinas XL", "ba_05_pegaxl", 35, "2");
        databaseHelper.addProduct("hojas pegatina", "ba_06_hojapega", 6, "5");
        databaseHelper.addProduct("marcapáginas", "ba_07_marca", 149, "2");
        databaseHelper.addProduct("marcapáginas magnético", "ba_08_marcamag", 19, "4");
        databaseHelper.addProduct("chapas", "ba_09_chap", 56, "2");
        databaseHelper.addProduct("llavero madera", "ba_10_llave", 25, "6");
        databaseHelper.addProduct("sujeta móviles", "ba_11_suje", 5, "6");
        databaseHelper.addProduct("pin plástico", "ba_12_pinpla", 18, "6");
        databaseHelper.addProduct("pin madera", "ba_13_pinma", 3, "6");
        databaseHelper.addProduct("memopad", "ba_14_memo", 7, "7");
        databaseHelper.addProduct("memopad pequeño", "ba_15_minimemo", 1, "5");
        databaseHelper.addProduct("pack etiquetas pokemon", "ba_16_etiqueta", 3, "6");
        databaseHelper.addProduct("espejo de mano", "ba_17_Espe", 13, "6");
        databaseHelper.addProduct("pizarra magnética", "ba_18_pizarra", 2, "10");
        //Amy
        databaseHelper.addProduct("Collar hoja", "A_01_c_hoja", 47, "35 €");
        databaseHelper.addProduct("Pendientes hoja", "A_02_p_hoja", 19, "45 €");
        databaseHelper.addProduct("Collar mariposa", "A_03_mariposa", 23, "45 €");
        databaseHelper.addProduct("Collar mariposa vitral", "A_04_vitral", 9, "60 €");
        databaseHelper.addProduct("Collar luna", "A_05_c_luna", 16, "40 €");
        databaseHelper.addProduct("Pendientes luna", "A_06_p_luna", 11, "40 €");
        databaseHelper.addProduct("Collar murciélago", "A_07_c_murcielago", 11, "33 €");
        databaseHelper.addProduct("Pendientes murciélago", "A_08_p_murcielago", 8, "27 €");
        databaseHelper.addProduct("Pin sushi", "A_09_pin_sushi", 10, "25 €");
        databaseHelper.addProduct("Collar sushi", "A_10_c_sushi", 10, "25 €");
        databaseHelper.addProduct("Pendientes sushi", "A_11_p_sushi", 18, "27 €");
        databaseHelper.addProduct("Pin One Piece", "A_12_pin_op", 41, "35 €");
        databaseHelper.addProduct("Pendientes katanas Zoro", "A_13_p_katanas", 6, "45 €");
        databaseHelper.addProduct("Collar pasado de fuego", "A_14_c_pasado_fuego", 25, "25 €");
        databaseHelper.addProduct("Pin Hollow Knight", "A_15_pin_hk", 36, "30 €");
        databaseHelper.addProduct("Pin Pokémon", "A_16_pin_pokemon", 26, "30 €");
        databaseHelper.addProduct("Pin Stardew Valley", "A_17_pin_sv", 33, "30 €");
        databaseHelper.addProduct("Collar líneas oblicuas", "A_18_c_lo", 8, "35 €");
        databaseHelper.addProduct("Pendientes líneas oblicuas", "A_19_p_lo", 6, "35 €");
        databaseHelper.addProduct("Collar animal crossing", "A_20_c_ac", 45, "30 €");
        databaseHelper.addProduct("Collar pesadilla antes de navidad", "A_21_c_pesadilla", 15, "20 €");
        databaseHelper.addProduct("Collar peces", "A_22_c_peces", 15, "25 €");
        databaseHelper.addProduct("Collar Mario Bros", "A_23_c_mario", 20, "30 €");
        databaseHelper.addProduct("Pines ranas", "A_24_pin_rana", 6, "30 €");
        databaseHelper.addProduct("Collar copo de nieve", "A_25_c_copo", 17, "37 €");
        //elena
        databaseHelper.addProduct("Print A3", "R_01_A3", 35, "8");
        databaseHelper.addProduct("Cinturón", "R_02_cin", 5, "12");
        databaseHelper.addProduct("Cinturón mini", "R_03_cinmin", 12, "6");
        databaseHelper.addProduct("Pendientes bola", "R_04_penbo", 6, "5");
        databaseHelper.addProduct("Pendientes", "R_05_pen", 6, "3");
        databaseHelper.addProduct("Pegatinas", "R_06_peg", 50, "1");
        databaseHelper.addProduct("Palillos", "R_07_pal", 6, "5");
        databaseHelper.addProduct("Tote bag n", "R_08_tbn", 4, "10");
        databaseHelper.addProduct("Tote bag esp", "R_09_tbe", 6, "12");
        databaseHelper.addProduct("Chain", "R_10_ch", 3, "3");
        databaseHelper.addProduct("Ts silence", "R_11_ts", 2, "15");
        databaseHelper.addProduct("Ts nina", "R_12_tn", 2, "15");
        databaseHelper.addProduct("Ts monster", "R_13_tm", 2, "15");
        databaseHelper.addProduct("Ts Queen", "R_14_tq", 3, "18");
        databaseHelper.addProduct("Ts nun", "R_15_tn", 4, "18");
        databaseHelper.addProduct("Ts chainsaw", "R_16_tc", 4, "18");
        databaseHelper.addProduct("Ts power", "R_17_tp", 5, "18");
        databaseHelper.addProduct("Ts skeleton", "R_18_tp", 2, "18");
        databaseHelper.addProduct("Promo queen", "R_19_pq", 1, "25");
        databaseHelper.addProduct("Top spooky", "R_20_tsp", 1, "12");
        databaseHelper.addProduct("Top ojos", "R_21_to", 4, "12");
        databaseHelper.addProduct("Top cherrys", "R_22_tch", 6, "12");
        databaseHelper.addProduct("Suda blanca cap spooky", "R_23_sbcs", 1, "38");
        databaseHelper.addProduct("Suda blanca cap chain", "R_24_sbcc", 2, "38");
        databaseHelper.addProduct("Suda negra power", "R_25_snp", 1, "35");
        databaseHelper.addProduct("Suda negra chain", "R_26_snc", 2, "35");
        databaseHelper.addProduct("Suda negra nun", "R_27_snn", 1, "35");
        databaseHelper.addProduct("Promo cap más puff", "R_28_cp", 1, "12");
        databaseHelper.addProduct("Chocker", "R_29_ch", 4, "10");
        databaseHelper.addProduct("Top mano", "R_30_t1", 3, "15");
        databaseHelper.addProduct("Chaqueta", "R_31_cqt", 1, "80");
        databaseHelper.addProduct("Top correas", "R_32_tco", 1, "45");
        databaseHelper.addProduct("Bolsas mano", "R_33_bm", 2, "22");
        databaseHelper.addProduct("Top tele", "R_34_tt", 1, "25");
        databaseHelper.addProduct("Chaleco", "R_35_chal", 1, "65");
        databaseHelper.addProduct("Cap oso", "R_36_co", 2, "22");
        databaseHelper.addProduct("Cap conejo", "R_37_cc", 1, "25");
        databaseHelper.addProduct("Cap lana", "R_38_cl", 5, "20");
        //joku
        databaseHelper.addProduct("Print A5", "J_01_A5", 100, "3");
        databaseHelper.addProduct("Print A4", "J_02_A4", 21, "5");
        databaseHelper.addProduct("Print A3", "J_03_A3", 31, "10");
        databaseHelper.addProduct("Cuaderno A6", "J_04_CUAA6", 2, "6");
        databaseHelper.addProduct("Cuaderno A5", "J_05_CUAA5", 6, "8");
        databaseHelper.addProduct("Poster A2", "J_06_POSTA26", 6, "20");
        databaseHelper.addProduct("Cartastarot", "J_07_tarot", 200, "4");
        databaseHelper.addProduct("Pack Pegas1", "J_08_Pegas1", 15, "3");
        databaseHelper.addProduct("Pack Pegas2", "J_09_Pegas2", 15, "2");
        databaseHelper.addProduct("Holo Pegas", "J_10_HoloPeg", 30, "2");
        databaseHelper.addProduct("ToteBag", "J_11_Tote", 13, "20");
        databaseHelper.addProduct("Holo Keychain", "J_12_H.Key", 60, "8");
        databaseHelper.addProduct("Keychain", "J_13_Key", 50, "7");
        databaseHelper.addProduct("Standee", "J_14_Stand", 11, "20");
        databaseHelper.addProduct("Lampara Led", "J_15_LampLed", 120, "30");
        databaseHelper.addProduct("Chapa", "J_16_chapas", 40, "3");
        databaseHelper.addProduct("Oferta tarot", "J_17_ofertar", 60, "10");
        databaseHelper.addProduct("Oferta Lampara", "J_18_oferLamp", 60, "50");
        databaseHelper.addProduct("OfertaChapa", "J_19_OferChap", 10, "5");
        databaseHelper.addProduct("OfertaStandee", "J_20_OferStand", 4, "35");
        //beru
        databaseHelper.addProduct("print A6", "BN_01_A6", 350, "4");
        databaseHelper.addProduct("print A5", "BN_02_A5", 200, "5");
        databaseHelper.addProduct("print A3", "BN_03_A3", 50, "10");
        databaseHelper.addProduct("llavero", "BN_04_charm", 300, "8");
        databaseHelper.addProduct("totebag blanca", "BN_05_toteB", 7, "12");
        databaseHelper.addProduct("totebag lino", "BN_06_toteL", 7, "15");
        databaseHelper.addProduct("taza", "BN_07_taza", 45, "12");
        databaseHelper.addProduct("gamuza", "BN_08_gamuza", 14, "3");
        databaseHelper.addProduct("bajara", "BN_09_baraja", 10, "30");
        databaseHelper.addProduct("tarot", "BN_10_tarot", 15, "25");
        databaseHelper.addProduct("gachatarot", "BN_11_gacha", 88, "2");
        databaseHelper.addProduct("setstickerpeq", "BN_12_SSS", 30, "5");
        databaseHelper.addProduct("setstickergrand", "BN_13_SSL", 20, "6");
        databaseHelper.addProduct("mousepad2mm", "BN_14_MP2", 2, "7");
        databaseHelper.addProduct("mousepad3mm", "BN_15_MP3", 2, "10");
        databaseHelper.addProduct("cojin peque", "BN_16_cojinS", 5, "12");
        databaseHelper.addProduct("cogin grande", "BN_17_cojinL", 16, "16");
        databaseHelper.addProduct("sticker", "BN_18_sticker", 100, "1");
        databaseHelper.addProduct("stand 10cm", "BN_19_stand10", 31, "10");
        databaseHelper.addProduct("stand 15cm", "BN_20_stand15", 10, "15");
        databaseHelper.addProduct("libreta A5", "BN_21_bookA5", 20, "12");
        databaseHelper.addProduct("libreta A6", "BN_22_bookA6", 20, "8");
        databaseHelper.addProduct("estuche", "BN_23_estuche", 3, "20");
        databaseHelper.addProduct("pin navidad", "BN_24_pinxmas", 2, "6");
        databaseHelper.addProduct("oferta printA5", "BN_25_3x12", 60, "12");
        databaseHelper.addProduct("oferta printA3", "BN_26_2x18", 60, "18");
        databaseHelper.addProduct("washitape", "BN_27_washi", 10, "4");
        databaseHelper.addProduct("cuadro 3d", "BN_28_3D", 3, "16");
        //claudia
        databaseHelper.addProduct("PKM_PIKACHU", "FF_01_PKM_PIKACHU", 2, "12");
        databaseHelper.addProduct("PKM_MIMIKIU", "FF_02_PKM_MIMIKIU", 4, "10");
        databaseHelper.addProduct("PKM_BULBAUSR", "FF_03_PKM_BULBAUSR", 4, "12");
        databaseHelper.addProduct("PKM_GENGAR", "FF_04_PKM_GENGAR", 3, "12");
        databaseHelper.addProduct("PKM_VULPIX", "FF_05_PKM_VULPIX", 3, "8");
        databaseHelper.addProduct("PKM_ODDISH", "FF_06_PKM_ODDISH", 2, "8");
        databaseHelper.addProduct("PKM_DRAGONITE", "FF_07_PKM_DRAGONITE", 2, "18");
        databaseHelper.addProduct("PKM_TOGEPI", "FF_08_PKM_TOGEPI", 3, "10");
        databaseHelper.addProduct("PKM_CHARMANDER", "FF_09_PKM_CHARMANDER", 3, "12");
        databaseHelper.addProduct("PKM_SQUIRTLE", "FF_10_PKM_SQUIRTLE", 4, "12");
        databaseHelper.addProduct("PKM_SNORLAX", "FF_11_PKM_SNORLAX", 1, "8");
        databaseHelper.addProduct("NAV_GRINCH", "FF_12_NAV_GRINCH", 6, "10");
        databaseHelper.addProduct("NAV_SOMBRERO_HP", "FF_13_NAV_SOMBRERO_HP", 4, "8");
        databaseHelper.addProduct("NAV_ESTRELLA_MUERTE", "FF_14_NAV_ESTRELLA_MUERTE", 7, "8");
        databaseHelper.addProduct("NAV_GROGU", "FF_15_NAV_GROGU", 4, "12");
        databaseHelper.addProduct("NAV_ALIEN", "FF_15_NAV_ALIEN", 4, "10");
        databaseHelper.addProduct("NAV_RICKYNILLO", "FF_16_NAV_RICKYNILLO", 4, "6");
        databaseHelper.addProduct("NAV_MONONOKE", "FF_17_NAV_MONONOKE", 4, "8");
        databaseHelper.addProduct("NAV_HALCON_MILENARIO", "FF_18_NAV_HALCON_MILENARIO", 1, "8");
        databaseHelper.addProduct("NAV_MANZANA", "FF_19_NAV_MANZANA", 3, "10");
        databaseHelper.addProduct("NAV_BAYMAX", "FF_20_NAV_BAYMAX", 2, "8");
        databaseHelper.addProduct("NAV_GIGANTE_HIERRO", "FF_21_NAV_GIGANTE_HIERRO", 3, "7");
        databaseHelper.addProduct("NAV_AERODESLIZADOR", "FF_22_NAV_AERODESLIZADOR", 4, "10");
        databaseHelper.addProduct("NAV_PONY_PISADOR", "FF_23_NAV_PONY_PISADOR", 3, "8");
        databaseHelper.addProduct("NAV_SHREK", "FF_24_NAV_SHREK", 9, "10");
        databaseHelper.addProduct("NAV_JIGGLYPUFF", "FF_25_NAV_JIGGLYPUFF", 5, "7");
        databaseHelper.addProduct("NAV_PIKACHU", "FF_26_NAV_PIKACHU", 5, "7");
        databaseHelper.addProduct("NAV_BULBASUR", "FF_27_NAV_BULBASUR", 5, "7");
        databaseHelper.addProduct("NAV_GENGAR", "FF_28_NAV_GENGAR", 5, "7");
        databaseHelper.addProduct("NAV_SQUIRTLE", "FF_29_NAV_SQUIRTLE", 5, "7");
        databaseHelper.addProduct("NAV_CHARMANDER", "FF_30_NAV_CHARMANDER", 5, "7");
        databaseHelper.addProduct("NAV_STITCH_ANGEL", "FF_31_NAV_STITCH_ANGEL", 10, "10");
        databaseHelper.addProduct("NAV_STITCH_1", "FF_32_NAV_STITCH_1", 10, "6");
        databaseHelper.addProduct("NAV_STITCH_2", "FF_33_NAV_STITCH_2", 10, "10");
        databaseHelper.addProduct("NAV_MICKEY", "FF_34_NAV_MICKEY", 16, "5");
        databaseHelper.addProduct("NAV_JACK_SKELLINGTON", "FF_35_NAV_JACK_SKELLINGTON", 6, "10");
        databaseHelper.addProduct("NAV_KIRBY", "FF_36_NAV_KIRBY", 4, "10");
        databaseHelper.addProduct("NAV_SETAS_MARIO", "FF_37_NAV_SETAS_MARIO", 7, "10");
        databaseHelper.addProduct("NAV_SIN_CARA", "FF_38_NAV_SIN_CARA", 6, "10");
        databaseHelper.addProduct("NAV_ZERO", "FF_39_NAV_ZERO", 5, "10");
        databaseHelper.addProduct("LLAVERO", "FF_40_LLAVERO", 20, "7");
        databaseHelper.addProduct("MAJORAS_MASK", "FF_41_MAJORAS_MASK", 1, "180");
        databaseHelper.addProduct("UKA_UKA", "FF_42_UKA_UKA", 1, "40");
        databaseHelper.addProduct("FIG_STITCH", "FF_43_FIG_STITCH", 3, "20");
        databaseHelper.addProduct("FIG_ANGEL", "FF_44_FIG_ANGEL", 2, "20");
        databaseHelper.addProduct("ESPE. SAKURA 1", "FF_45_ESPE. SAKURA 1", 3, "12");
        databaseHelper.addProduct("ESPE. SAKURA CORA", "FF_46_ESPE. SAKURA CORA", 3, "12");
        //noe
        databaseHelper.addProduct("vestidos genshin", "P_01_dress", 27, "15");
        databaseHelper.addProduct("leggings genshin", "P_02_legg", 54, "12");
        databaseHelper.addProduct("cuadernos a5 no face", "P_03_cuad", 28, "10");
        databaseHelper.addProduct("headbands klee", "P_04_headb", 14, "5");
        databaseHelper.addProduct("scrunchies klee", "P_05_scr", 45, "3");



        findViewById(R.id.scanButton).setOnClickListener(view -> startBarcodeScanning());



        findViewById(R.id.exportButton).setOnClickListener(view -> {
            // Implement export functionality
            String exportPath = getExternalFilesDir(null) + "/exported_database.csv";
            databaseHelper.exportDatabase(exportPath);
            Toast.makeText(MainActivity.this, "Database exported to " + exportPath, Toast.LENGTH_SHORT).show();
        });


        findViewById(R.id.exportSalesButton).setOnClickListener(view -> exportSales());
        findViewById(R.id.viewAllStockButton).setOnClickListener(view -> {
            // Implement view all stock functionality
            Intent intent = new Intent(MainActivity.this, ViewAllStockActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.viewAllSalesButton).setOnClickListener(view -> {
            // Implement view all sales functionality
            Intent intent = new Intent(MainActivity.this, ViewAllSalesActivity.class);
            startActivity(intent);
        });



    }



    private void exportSales() {
        // Implement export sales functionality
        String exportPath = getExternalFilesDir(null) + "/exported_sales.csv";
        databaseHelper.exportSales(exportPath);
        Toast.makeText(MainActivity.this, "Sales exported to " + exportPath, Toast.LENGTH_SHORT).show();
    }



    private void startBarcodeScanning() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(true);  // Set orientation locked to true
        integrator.setCaptureActivity(CustomScannerActivity.class);  // CustomScannerActivity for vertical scanning
        integrator.initiateScan();
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            // Barcode was successfully scanned
            String scannedBarcode = result.getContents();
            Toast.makeText(this, "Scanned Barcode: " + scannedBarcode, Toast.LENGTH_SHORT).show();
            // You can return the scannedBarcode to the calling method or use it as needed

            // Handle the scanned barcode as required
            handleScannedBarcode(scannedBarcode);
        } else {
            // Barcode scanning was unsuccessful or canceled
            Toast.makeText(this, "Barcode scanning unsuccessful or canceled", Toast.LENGTH_SHORT).show();
        }
    }



    private void handleScannedBarcode(String barcode) {
        // Query the database for product information based on the scanned barcode
        databaseHelper.queryProductInfo(barcode, new ProductInfoCallback() {
            @Override
            public void onProductInfoReceived(ProductInfo productInfo) {
                // Now you have the product information, launch the ProductInfoActivity
                Intent intent = new Intent(MainActivity.this, ProductInfoActivity.class);
                intent.putExtra("productName", productInfo.getName());
                intent.putExtra("barcode", productInfo.getBarcode());
                intent.putExtra("stock", productInfo.getStock());
                intent.putExtra("price", productInfo.getPrice());
                startActivity(intent);
            }

            @Override
            public void onProductInfoError(String errorMessage) {
                // Handle the error, such as showing a toast message
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }



    // Define an interface for the product information callback
    public interface ProductInfoCallback {
        void onProductInfoReceived(ProductInfo productInfo);

        void onProductInfoError(String errorMessage);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}