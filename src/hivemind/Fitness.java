/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hivemind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Gia
 */
public class Fitness {

    BufferedReader br = null;
    private final HashMap<Double, Double> sampleDataPoints; // FINTESS CLASS
    
    public Fitness() {
        sampleDataPoints = new HashMap<>(); // FITNESS CLASS
        fillSampleDataPoints(); // FINTESS CLASS
    }
   
    
    /**
     * Method to print ou the GNU commands for plotting
     * @param filePath // your local file path
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @return 
     */
    public String printPlotGNUCommands(String filePath, double a, double b, double c, double d, double e, double f) {

        String command = "plot \"" + filePath + "\", " + a + " + (" + b + "*x)+ " + "(" + c + "*(x**2))+" + "(" + d + "*(x**3))+" + "(" + e + "*(x**4)) + " + "(" + f + "*(x**5))";
        return command;

    }
 
    //Method to calculate the current curve fitness.  // Fitness is more fit the closer it is to zero
    public double calculateCurveFitness(double a, double b, double c, double d, double e, double f, boolean print) {

        double fitness = 0;
        
        for (Map.Entry entry : sampleDataPoints.entrySet()) {

            Double xSample = (Double) entry.getKey();
            Double yValue = (Double) entry.getValue();
          
            Double yValFromCurrentX = getYValFromFunctionX(a, b, c, d, e, f, xSample, print);
            
            if(print){
                System.out.println("X is " + xSample + " , \nY is : " + yValFromCurrentX + "\nY from Data is..." + yValue);
            }        
            Double yDifference = (yValFromCurrentX - yValue);
            yDifference = Math.abs(yDifference);
            if(print){
                System.out.println("Difference is... " + yDifference + "\n");
            }
            fitness += yDifference;

        }
        if(print){
            System.out.println("Final curve fitness for a:" + a + " b: " + b + " c: " + c + " d: " + d + " e: " + e + " f: " + f + " ");
            System.out.println("Fitness : " + fitness);
        }
        return fitness; // use getYValFromFunctionX // and hash map of data samples

    }
    
    /**
     * Work out the Value Y for any given value X, and any given curve with coefficients a,b,c,d,e,f
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param x // any sample X value
     * @param print // if boolean print, it prints out stats
     * @return 
     */
    public double getYValFromFunctionX(double a, double b, double c, double d, double e, double f, double x, boolean print) {
        double yVal = a + (b * x) + (c * (Math.pow(x, 2))) + (d * (Math.pow(x, 3))) + (e * (Math.pow(x, 4))) + (f * (Math.pow(x, 5)));
        
        if(print){
            System.out.println("Yval is..." + yVal);
        }
        return yVal;

    }
    
    /**
     * Constructor method 
     * Fill sampleData points in the hash map, from the data graph
     */
    private void fillSampleDataPoints() {

        sampleDataPoints.put(-25.0, 1247265.625);
        sampleDataPoints.put(-24.501616379310345, 1161679.2075664313);
        sampleDataPoints.put(-24.00323275862069, 1080221.7865481253);
        sampleDataPoints.put(-23.504849137931036, 1002770.888358754);
        sampleDataPoints.put(-23.00646551724138, 929205.6978169009);
        sampleDataPoints.put(-22.508081896551726, 859407.0544562864);
        sampleDataPoints.put(-22.00969827586207, 793257.4488359896);
        sampleDataPoints.put(-21.511314655172413, 730641.0188506725);
        sampleDataPoints.put(-21.012931034482758, 671443.5460408046);
        sampleDataPoints.put(-20.514547413793103, 615552.451902885);
        sampleDataPoints.put(-20.016163793103445, 562856.7941996658);
        sampleDataPoints.put(-19.51778017241379, 513247.2632703781);
        sampleDataPoints.put(-19.019396551724135, 466616.1783409525);
        sampleDataPoints.put(-18.52101293103448, 422857.4838342449);
        sampleDataPoints.put(-18.022629310344826, 381866.74568025896);
        sampleDataPoints.put(-17.52424568965517, 343541.14762637013);
        sampleDataPoints.put(-17.025862068965516, 307779.4875475493);
        sampleDataPoints.put(-16.52747844827586, 274482.1737565859);
        sampleDataPoints.put(-16.029094827586206, 243551.22131431225);
        sampleDataPoints.put(-15.530711206896552, 214890.24833982633);
        sampleDataPoints.put(-15.032327586206895, 188404.47232071587);
        sampleDataPoints.put(-14.533943965517238, 164000.70642328204);
        sampleDataPoints.put(-14.035560344827584, 141587.35580276255);
        sampleDataPoints.put(-13.537176724137929, 121074.41391355566);
        sampleDataPoints.put(-13.038793103448274, 102373.45881944349);
        sampleDataPoints.put(-12.789601293103447, 93675.27684907736);
        sampleDataPoints.put(-12.54040948275862, 85397.64950381576);
        sampleDataPoints.put(-12.291217672413792, 77529.96806991717);
        sampleDataPoints.put(-12.042025862068964, 70061.72217989342);
        sampleDataPoints.put(-11.792834051724137, 62982.4996972043);
        sampleDataPoints.put(-11.54364224137931, 56281.986600952);
        sampleDataPoints.put(-11.294450431034482, 49949.96687057562);
        sampleDataPoints.put(-11.045258620689655, 43976.32237054556);
        sampleDataPoints.put(-10.796066810344827, 38351.0327350581);
        sampleDataPoints.put(-10.546875, 33064.17525272991);
        sampleDataPoints.put(-10.297683189655173, 28105.924751292438);
        sampleDataPoints.put(-10.048491379310345, 23466.553482286447);
        sampleDataPoints.put(-9.799299568965518, 19136.43100575655);
        sampleDataPoints.put(-9.55010775862069, 15106.02407494562);
        sampleDataPoints.put(-9.300915948275861, 11365.896520989289);
        sampleDataPoints.put(-9.051724137931034, 7906.70913761054);
        sampleDataPoints.put(-8.802532327586206, 4719.219565814019);
        sampleDataPoints.put(-8.553340517241377, 1794.2821785806257);
        sampleDataPoints.put(-8.30414870689655, -877.1520344379536);
        sampleDataPoints.put(-8.054956896551722, -3304.0355822248684);
        sampleDataPoints.put(-7.805765086206895, -5495.224587703571);
        sampleDataPoints.put(-7.556573275862068, -7459.478903043302);
        sampleDataPoints.put(-7.30738146551724, -9205.462224964629);
        sampleDataPoints.put(-7.058189655172413, -10741.742210044958);
        sampleDataPoints.put(-6.808997844827585, -12076.79059002407);
        sampleDataPoints.put(-6.559806034482758, -13218.98328710957);
        sampleDataPoints.put(-6.310614224137931, -14176.600529282463);
        sampleDataPoints.put(-6.061422413793102, -14957.826965602637);
        sampleDataPoints.put(-5.812230603448274, -15570.75178151437);
        sampleDataPoints.put(-5.563038793103447, -16023.368814151876);
        sampleDataPoints.put(-5.313846982758619, -16323.576667644787);
        sampleDataPoints.put(-5.064655172413792, -16479.178828423665);
        sampleDataPoints.put(-4.815463362068964, -16497.88378052553);
        sampleDataPoints.put(-4.566271551724137, -16387.305120899393);
        sampleDataPoints.put(-4.31707974137931, -16154.961674711707);
        sampleDataPoints.put(-4.067887931034481, -15808.277610651941);
        sampleDataPoints.put(-3.818696120689654, -15354.582556238061);
        sampleDataPoints.put(-3.5695043103448265, -14801.11171312205);
        sampleDataPoints.put(-3.320312499999999, -14155.005972395427);
        sampleDataPoints.put(-3.0711206896551713, -13423.312029894747);
        sampleDataPoints.put(-2.8219288793103434, -12612.982501507124);
        sampleDataPoints.put(-2.572737068965516, -11730.876038475753);
        sampleDataPoints.put(-2.3235452586206886, -10783.757442705393);
        sampleDataPoints.put(-2.074353448275861, -9778.297782067913);
        sampleDataPoints.put(-1.8251616379310336, -8721.07450570778);
        sampleDataPoints.put(-1.575969827586206, -7618.571559347589);
        sampleDataPoints.put(-1.3267780172413783, -6477.179500593566);
        sampleDataPoints.put(-1.0775862068965507, -5303.195614241088);
        sampleDataPoints.put(-0.8283943965517231, -4102.824027580185);
        sampleDataPoints.put(-0.5792025862068955, -2882.1758257010683);
        sampleDataPoints.put(-0.3300107758620679, -1647.269166799627);
        sampleDataPoints.put(-8.08189655172403300e-2, -404.02939748295404);
        sampleDataPoints.put(0.16837284482758727, 841.7108319251483);
        sampleDataPoints.put(0.41756465517241487, 2084.211452078652);
        sampleDataPoints.put(0.6667564655172424, 3317.8248593037924);
        sampleDataPoints.put(0.9159482758620701, 4536.995800293555);
        sampleDataPoints.put(1.1651400862068977, 5736.261256802159);
        sampleDataPoints.put(1.414331896551725, 6910.250330339549);
        sampleDataPoints.put(1.6635237068965527, 8053.684126865884);
        sampleDataPoints.put(1.9127155172413803, 9161.37564148602);
        sampleDataPoints.put(2.161907327586208, 10228.229643143992);
        sampleDataPoints.put(2.4110991379310356, 11249.242559317505);
        sampleDataPoints.put(2.660290948275863, 12219.502360712442);
        sampleDataPoints.put(2.909482758620691, 13134.188445957316);
        sampleDataPoints.put(3.1586745689655187, 13988.571526297772);
        sampleDataPoints.put(3.407866379310346, 14778.013510291084);
        sampleDataPoints.put(3.6570581896551735, 15497.967388500627);
        sampleDataPoints.put(3.906250000000001, 16143.977118190378);
        sampleDataPoints.put(4.155441810344829, 16711.677508019395);
        sampleDataPoints.put(4.404633620689657, 17196.794102736298);
        sampleDataPoints.put(4.653825431034484, 17595.143067873756);
        sampleDataPoints.put(4.903017241379311, 17902.631074443);
        sampleDataPoints.put(5.152209051724139, 18115.25518362829);
        sampleDataPoints.put(5.401400862068966, 18229.102731481376);
        sampleDataPoints.put(5.650592672413794, 18240.351213616053);
        sampleDataPoints.put(5.899784482758622, 18145.268169902574);
        sampleDataPoints.put(6.14897629310345, 17940.21106916219);
        sampleDataPoints.put(6.398168103448278, 17621.627193861597);
        sampleDataPoints.put(6.647359913793105, 17186.053524807467);
        sampleDataPoints.put(6.896551724137932, 16630.116625840896);
        sampleDataPoints.put(7.14574353448276, 15950.532528531914);
        sampleDataPoints.put(7.394935344827587, 15144.106616873973);
        sampleDataPoints.put(7.644127155172415, 14207.733511978398);
        sampleDataPoints.put(7.893318965517243, 13138.39695676892);
        sampleDataPoints.put(8.142510775862071, 11933.169700676157);
        sampleDataPoints.put(8.391702586206899, 10589.213384332068);
        sampleDataPoints.put(8.640894396551726, 9103.778424264478);
        sampleDataPoints.put(8.890086206896553, 7474.203897591538);
        sampleDataPoints.put(9.38846982758621, 3772.4350640208004);
        sampleDataPoints.put(9.886853448275865, -535.61166923765);
        sampleDataPoints.put(10.38523706896552, -5468.043665148818);
        sampleDataPoints.put(10.883620689655174, -11041.560786561518);
        sampleDataPoints.put(11.880387931034484, -24171.63449536471);
        sampleDataPoints.put(12.877155172413794, -40031.42190761884);
        sampleDataPoints.put(13.873922413793103, -58704.2282791277);
        sampleDataPoints.put(14.870689655172415, -80251.19308237245);
        sampleDataPoints.put(15.867456896551726, -104711.40807935702);
        sampleDataPoints.put(16.864224137931036, -132102.03539445304);
        sampleDataPoints.put(17.860991379310345, -162418.42558724518);
        sampleDataPoints.put(18.857758620689655, -195634.2357253767);
        sampleDataPoints.put(19.35614224137931, -213315.2883503696);
        sampleDataPoints.put(19.854525862068968, -231701.54745739434);
        sampleDataPoints.put(20.352909482758623, -250783.67146461122);
        sampleDataPoints.put(20.851293103448278, -270550.9850855932);
        sampleDataPoints.put(21.349676724137932, -290991.4830191017);
        sampleDataPoints.put(21.848060344827587, -312091.8336388629);
        sampleDataPoints.put(22.346443965517242, -333837.38268334453);
        sampleDataPoints.put(22.844827586206897, -356212.156945532);
        sampleDataPoints.put(23.34321120689655, -379198.867962705);
        sampleDataPoints.put(23.841594827586206, -402778.91570621356);
        sampleDataPoints.put(24.33997844827586, -426932.3922712551);
        sampleDataPoints.put(24.838362068965516, -451638.0855666504);
        sampleDataPoints.put(25.33674568965517, -476873.4830046196);
        sampleDataPoints.put(25.835129310344826, -502614.7751905597);
        sampleDataPoints.put(26.333512931034484, -528836.8596128204);
        sampleDataPoints.put(26.83189655172414, -555513.3443324799);
        sampleDataPoints.put(27.330280172413794, -582616.5516731226);
        sampleDataPoints.put(27.828663793103452, -610117.5219106139);
        sampleDataPoints.put(28.327047413793107, -637986.0169628783);
        sampleDataPoints.put(28.82543103448276, -666190.5240796745);
        sampleDataPoints.put(29.323814655172416, -694698.2595323736);
        sampleDataPoints.put(29.82219827586207, -723475.1723037325);
        sampleDataPoints.put(30.320581896551726, -752485.9477776726);
        sampleDataPoints.put(30.81896551724138, -781694.0114290569);
        sampleDataPoints.put(31.317349137931036, -811061.532513464);
        sampleDataPoints.put(31.81573275862069, -840549.4277569654);
        sampleDataPoints.put(32.31411637931035, -870117.3650459038);
        sampleDataPoints.put(32.8125, -899723.7671166658);
        sampleDataPoints.put(33.31088362068965, -929325.8152454628);
        sampleDataPoints.put(33.80926724137931, -958879.452938103);
        sampleDataPoints.put(34.30765086206897, -988339.3896197717);
        sampleDataPoints.put(34.80603448275862, -1017659.1043248044);
        sampleDataPoints.put(35.30441810344827, -1046790.8493864647);
        sampleDataPoints.put(35.80280172413793, -1075685.654126723);
        sampleDataPoints.put(36.30118534482759, -1104293.3285460277);
        sampleDataPoints.put(36.79956896551724, -1132562.4670130853);
        sampleDataPoints.put(37.2979525862069, -1160440.4519546374);
        sampleDataPoints.put(37.796336206896555, -1187873.4575452358);
        sampleDataPoints.put(38.294719827586206, -1214806.453397016);
        sampleDataPoints.put(38.793103448275865, -1241183.2082494805);
        sampleDataPoints.put(39.29148706896552, -1266946.2936592684);
        sampleDataPoints.put(39.789870689655174, -1292037.0876899352);
        sampleDataPoints.put(40.28825431034483, -1316395.778601729);
        sampleDataPoints.put(40.78663793103449, -1339961.368541367);
        sampleDataPoints.put(41.28502155172414, -1362671.67723181);
        sampleDataPoints.put(41.7834051724138, -1384463.3456620423);
        sampleDataPoints.put(42.28178879310346, -1405271.8397768445);
        sampleDataPoints.put(42.78017241379311, -1425031.4541665702);
        sampleDataPoints.put(43.27855603448276, -1443675.315756929);
        sampleDataPoints.put(43.77693965517242, -1461135.3874987534);
        sampleDataPoints.put(44.27532327586208, -1477342.472057779);
        sampleDataPoints.put(44.77370689655173, -1492226.2155044232);
        sampleDataPoints.put(45.022898706896555, -1499149.5333591057);
        sampleDataPoints.put(45.27209051724138, -1505715.1110035584);
        sampleDataPoints.put(45.521282327586206, -1511913.8305472552);
        sampleDataPoints.put(45.77047413793104, -1517736.502504293);
        sampleDataPoints.put(46.01966594827587, -1523173.8659086982);
        sampleDataPoints.put(46.2688577586207, -1528216.5884297383);
        sampleDataPoints.put(46.51804956896552, -1532855.2664872203);
        sampleDataPoints.put(46.76724137931035, -1537080.4253667989);
        sampleDataPoints.put(47.016433189655174, -1540882.519335281);
        sampleDataPoints.put(47.265625, -1544251.9317559346);
        sampleDataPoints.put(47.514816810344826, -1547178.9752037898);
        sampleDataPoints.put(47.76400862068966, -1549653.8915809486);
        sampleDataPoints.put(48.01320043103449, -1551666.8522318855);
        sampleDataPoints.put(48.26239224137932, -1553207.9580587558);
        sampleDataPoints.put(48.51158405172414, -1554267.2396367048);
        sampleDataPoints.put(48.76077586206897, -1554834.6573291656);
        sampleDataPoints.put(49.009967672413794, -1554900.1014031693);
        sampleDataPoints.put(49.25915948275862, -1554453.3921446507);
        sampleDataPoints.put(49.508351293103445, -1553484.2799737533);
        sampleDataPoints.put(49.75754310344828, -1551982.4455601315);
        sampleDataPoints.put(50.00673491379311, -1549937.4999382622);
        sampleDataPoints.put(50.255926724137936, -1547338.9846227462);
        sampleDataPoints.put(50.50511853448276, -1544176.3717236167);
        sampleDataPoints.put(50.75431034482759, -1540439.0640616347);
        sampleDataPoints.put(51.25269396551724, -1531197.6299777045);
        sampleDataPoints.put(51.7510775862069, -1519528.523533415);
        sampleDataPoints.put(52.249461206896555, -1505344.4846425515);
        sampleDataPoints.put(52.747844827586206, -1488557.1556600048);
        sampleDataPoints.put(53.24622844827586, -1469077.0850715397);
        sampleDataPoints.put(53.744612068965516, -1446813.731183584);
        sampleDataPoints.put(54.242995689655174, -1421675.4658129867);
        sampleDataPoints.put(54.741379310344826, -1393569.5779768121);
        sampleDataPoints.put(55.23976293103448, -1362402.2775821104);
        sampleDataPoints.put(55.738146551724135, -1328078.6991156852);
        sampleDataPoints.put(56.236530172413794, -1290502.905333885);
        sampleDataPoints.put(56.734913793103445, -1249577.8909523725);
        sampleDataPoints.put(57.233297413793096, -1205205.586335896);
        sampleDataPoints.put(57.731681034482754, -1157286.8611880674);
        sampleDataPoints.put(58.23006465517241, -1105721.5282411529);
        sampleDataPoints.put(58.728448275862064, -1050408.346945833);
        sampleDataPoints.put(59.226831896551715, -991245.0271609786);
        sampleDataPoints.put(59.725215517241374, -928128.2328434398);
        sampleDataPoints.put(60.22359913793103, -860953.5857378122);
        sampleDataPoints.put(60.72198275862068, -789615.6690662205);
        sampleDataPoints.put(61.22036637931034, -714008.0312180836);
        sampleDataPoints.put(61.71875, -634023.189439904);
        sampleDataPoints.put(62.21713362068965, -549552.6335250409);
        sampleDataPoints.put(62.71551724137931, -460486.8295034771);
        sampleDataPoints.put(63.71228448275862, -268126.2445820186);
        sampleDataPoints.put(64.70905172413794, -56044.82785953162);
        sampleDataPoints.put(65.70581896551724, 176670.1715481677);
        sampleDataPoints.put(66.70258620689656, 430947.5305206736);
        sampleDataPoints.put(67.69935344827587, 707731.9338600705);
        sampleDataPoints.put(68.69612068965517, 1007983.8562181438);
        sampleDataPoints.put(69.69288793103448, 1332679.4440234974);
        sampleDataPoints.put(70.6896551724138, 1682810.3974087455);
        sampleDataPoints.put(71.68642241379311, 2059383.852137636);
        sampleDataPoints.put(72.68318965517241, 2463422.261532208);
        sampleDataPoints.put(73.67995689655173, 2895963.2783999797);
        sampleDataPoints.put(74.67672413793105, 3358059.6369610527);
        sampleDataPoints.put(75.67349137931035, 3850779.034775294);
        sampleDataPoints.put(76.67025862068967, 4375204.014669511);
        sampleDataPoints.put(77.66702586206898, 4932431.846664574);
        sampleDataPoints.put(78.66379310344828, 5523574.409902563);
        sampleDataPoints.put(79.66056034482759, 6149758.074573956);
        sampleDataPoints.put(80.6573275862069, 6812123.583844798);
        sampleDataPoints.put(81.65409482758622, 7511825.935783766);
        sampleDataPoints.put(82.65086206896552, 8250034.265289441);
        sampleDataPoints.put(83.64762931034483, 9027931.72601734);
        sampleDataPoints.put(84.64439655172414, 9846715.372307226);
        sampleDataPoints.put(85.64116379310346, 10707596.041110113);
        sampleDataPoints.put(86.63793103448276, 11611798.23391547);
        sampleDataPoints.put(87.63469827586206, 12560559.998678416);
        sampleDataPoints.put(88.63146551724138, 13555132.81174687);
        sampleDataPoints.put(89.6282327586207, 14596781.459788635);
        sampleDataPoints.put(90.625, 15686783.921718597);
        sampleDataPoints.put(91.6217672413793, 16826431.250625923);
        sampleDataPoints.put(92.61853448275862, 18017027.455701165);
        sampleDataPoints.put(93.61530172413794, 19259889.3841634);
        sampleDataPoints.put(94.61206896551724, 20556346.603187412);
        sampleDataPoints.put(95.60883620689654, 21907741.281830862);
        sampleDataPoints.put(96.60560344827586, 23315428.07296145);
        sampleDataPoints.put(97.60237068965517, 24780773.99518399);
        sampleDataPoints.put(98.59913793103448, 26305158.314767607);
        sampleDataPoints.put(99.5959051724138, 27889972.427573);
        sampleDataPoints.put(100.59267241379311, 29536619.740979426);
        sampleDataPoints.put(101.58943965517241, 31246515.555811904);
        sampleDataPoints.put(102.58620689655173, 33021086.9482685);
        sampleDataPoints.put(103.58297413793105, 34861772.65184732);
        sampleDataPoints.put(104.57974137931035, 36770022.939273626);
        sampleDataPoints.put(105.57650862068967, 38747299.50442727);
        sampleDataPoints.put(106.57327586206898, 40795075.34426952);
        sampleDataPoints.put(107.57004310344828, 42914834.640770435);
        sampleDataPoints.put(108.5668103448276, 45108072.642835945);
        sampleDataPoints.put(109.56357758620692, 47376295.54823498);
        sampleDataPoints.put(110.56034482758622, 49721020.385526605);
        sampleDataPoints.put(111.55711206896552, 52143774.89598731);
        sampleDataPoints.put(112.55387931034484, 54646097.41553809);
        sampleDataPoints.put(113.55064655172416, 57229536.7566715);
        sampleDataPoints.put(114.54741379310346, 59895652.09037884);
        sampleDataPoints.put(115.54418103448276, 62646012.82807757);
        sampleDataPoints.put(116.54094827586208, 65482198.50353812);
        sampleDataPoints.put(117.5377155172414, 68405798.65481119);
        sampleDataPoints.put(118.5344827586207, 71418412.70615494);
        sampleDataPoints.put(120.52801724137932, 77717128.92868692);
        sampleDataPoints.put(122.52155172413794, 84391335.80257945);
        sampleDataPoints.put(124.51508620689656, 91454172.58193459);
        sampleDataPoints.put(126.50862068965517, 98918925.36517979);
        sampleDataPoints.put(128.50215517241378, 106799023.31673703);
        sampleDataPoints.put(130.4956896551724, 115108034.88869216);
        sampleDataPoints.put(132.48922413793105, 123859664.0424628);
        sampleDataPoints.put(134.48275862068965, 133067746.47046836);
        sampleDataPoints.put(136.47629310344826, 142746245.81779835);
        sampleDataPoints.put(138.4698275862069, 152909249.90388212);
        sampleDataPoints.put(140.46336206896552, 163570966.94415662);
        sampleDataPoints.put(142.45689655172413, 174745721.7717359);
        sampleDataPoints.put(144.45043103448276, 186447952.059081);
        sampleDataPoints.put(146.4439655172414, 198692204.5396672);
        sampleDataPoints.put(148.4375, 211493131.22965395);
        sampleDataPoints.put(150.43103448275863, 224865485.64955425);
        sampleDataPoints.put(152.42456896551727, 238824119.0459027);
        sampleDataPoints.put(154.41810344827587, 253383976.61292428);
        sampleDataPoints.put(156.41163793103448, 268560093.71420467);
        sampleDataPoints.put(158.4051724137931, 284367592.1043583);
        sampleDataPoints.put(160.39870689655174, 300821676.15069675);
        sampleDataPoints.put(162.39224137931035, 317937629.0548985);
        sampleDataPoints.put(164.38577586206895, 335730809.074678);
        sampleDataPoints.put(166.3793103448276, 354216645.7454546);
        sampleDataPoints.put(168.37284482758622, 373410636.10202026);
        sampleDataPoints.put(170.36637931034483, 393328340.9002099);
        sampleDataPoints.put(172.35991379310346, 413985380.8385705);
        sampleDataPoints.put(174.3534482758621, 435397432.78002894);
        sampleDataPoints.put(176.3469827586207, 457580225.9735608);
        sampleDataPoints.put(178.34051724137933, 480549538.2758618);
        sampleDataPoints.put(180.33405172413796, 504321192.3730132);
        sampleDataPoints.put(182.32758620689657, 528911052.0021529);
        sampleDataPoints.put(184.32112068965517, 554335018.1731448);
        sampleDataPoints.put(186.3146551724138, 580609025.3902466);
        sampleDataPoints.put(188.30818965517244, 607749037.8737787);
        sampleDataPoints.put(190.30172413793105, 635771045.7817934);
        sampleDataPoints.put(192.29525862068965, 664691061.4317447);
        sampleDataPoints.put(194.28879310344828, 694525115.5221565);
        sampleDataPoints.put(196.28232758620692, 725289253.3542912);
        sampleDataPoints.put(198.27586206896552, 756999531.0538187);
        sampleDataPoints.put(200.26939655172413, 789672011.7924867);
        sampleDataPoints.put(202.26293103448276, 823322762.0097883);
        sampleDataPoints.put(204.2564655172414, 857967847.6346316);
        sampleDataPoints.put(206.25, 893623330.3070068);
        sampleDataPoints.put(208.24353448275863, 930305263.5996592);
        sampleDataPoints.put(210.23706896551727, 968029689.239754);
        sampleDataPoints.put(212.23060344827587, 1006812633.3305464);
        sampleDataPoints.put(214.2241379310345, 1046670102.5730538);
        sampleDataPoints.put(216.21767241379314, 1087618080.4877193);
        sampleDataPoints.put(218.21120689655174, 1129672523.6360838);
        sampleDataPoints.put(220.20474137931035, 1172849357.8424554);
        sampleDataPoints.put(222.19827586206898, 1217164474.4155788);
        sampleDataPoints.put(224.19181034482762, 1262633726.3702998);
        sampleDataPoints.put(226.18534482758622, 1309272924.6492386);
        sampleDataPoints.put(228.17887931034483, 1357097834.3444586);
        sampleDataPoints.put(230.17241379310346, 1406124170.9191344);
        sampleDataPoints.put(234.1594827586207, 1507843715.7451153);
        sampleDataPoints.put(238.14655172413796, 1614556146.678215);
        sampleDataPoints.put(242.1336206896552, 1726385015.3940568);
        sampleDataPoints.put(246.12068965517244, 1843452716.7862606);
        sampleDataPoints.put(250.10775862068968, 1965880368.059853);
        sampleDataPoints.put(254.09482758620692, 2093787687.8246672);
        sampleDataPoints.put(258.0818965517242, 2227292875.1887574);
        sampleDataPoints.put(262.0689655172414, 2366512488.851795);
        sampleDataPoints.put(266.0560344827586, 2511561326.1984863);
        sampleDataPoints.put(270.0431034482759, 2662552302.3919744);
        sampleDataPoints.put(274.03017241379314, 2819596329.4672413);
        sampleDataPoints.put(278.01724137931035, 2982802195.424514);
        sampleDataPoints.put(282.00431034482756, 3152276443.3226867);
        sampleDataPoints.put(285.9913793103448, 3328123250.3727093);
        sampleDataPoints.put(289.9784482758621, 3510444307.031);
        sampleDataPoints.put(293.9655172413793, 3699338696.0928493);
        sampleDataPoints.put(297.95258620689657, 3894902771.7858386);
        sampleDataPoints.put(301.93965517241384, 4097230038.863228);
        sampleDataPoints.put(305.92672413793105, 4306411031.697372);
        sampleDataPoints.put(309.9137931034483, 4522533193.373136);
        sampleDataPoints.put(313.9008620689656, 4745680754.781286);
        sampleDataPoints.put(317.8879310344828, 4975934613.711895);
        sampleDataPoints.put(321.875, 5213372213.947773);
        sampleDataPoints.put(325.86206896551727, 5458067424.357848);
        sampleDataPoints.put(333.83620689655174, 5969507551.16736);
        sampleDataPoints.put(341.8103448275862, 6510769852.3638525);
        sampleDataPoints.put(349.7844827586207, 7082304249.526779);
        sampleDataPoints.put(357.7586206896552, 7684491858.580656);
        sampleDataPoints.put(365.73275862068965, 8317641120.784062);
        sampleDataPoints.put(373.7068965517241, 8981983933.718641);
        sampleDataPoints.put(381.6810344827586, 9677671782.27813);
        sampleDataPoints.put(389.65517241379314, 10404771869.657326);
        sampleDataPoints.put(397.6293103448276, 11163263248.341145);
        sampleDataPoints.put(405.6034482758621, 11953032951.093582);
        sampleDataPoints.put(413.57758620689657, 12773872121.946762);
        sampleDataPoints.put(421.55172413793105, 13625472147.18991);
        sampleDataPoints.put(425.53879310344826, 14062682808.775856);
        sampleDataPoints.put(429.5258620689655, 14507420786.35838);
        sampleDataPoints.put(433.5129310344828, 14959617723.439468);
        sampleDataPoints.put(437.5, 15419198303.222656);
        sampleDataPoints.put(441.48706896551727, 15886080127.706377);
        sampleDataPoints.put(445.47413793103453, 16360173596.777374);
        sampleDataPoints.put(449.46120689655174, 16841381787.304142);
        sampleDataPoints.put(453.448275862069, 17329600332.230297);
        sampleDataPoints.put(457.4353448275863, 17824717299.668);
        sampleDataPoints.put(461.4224137931035, 18326613071.991356);
        sampleDataPoints.put(465.4094827586207, 18835160224.929813);
        sampleDataPoints.put(469.39655172413796, 19350223406.661625);
        sampleDataPoints.put(473.38362068965523, 19871659216.907166);
        sampleDataPoints.put(477.37068965517244, 20399316086.02238);
        sampleDataPoints.put(481.35775862068965, 20933034154.092228);
        sampleDataPoints.put(485.3448275862069, 21472645150.024055);
        sampleDataPoints.put(489.3318965517242, 22017972270.640957);
        sampleDataPoints.put(493.3189655172414, 22568830059.77526);
        sampleDataPoints.put(497.30603448275866, 23125024287.361916);
        sampleDataPoints.put(501.29310344827593, 23686351828.531834);
        sampleDataPoints.put(505.28017241379314, 24252600542.70539);
        sampleDataPoints.put(509.2672413793104, 24823549152.685772);
        sampleDataPoints.put(513.2543103448277, 25398967123.752388);
        sampleDataPoints.put(517.2413793103449, 25978614542.754288);
        sampleDataPoints.put(521.2284482758621, 26562241997.203575);
        sampleDataPoints.put(525.2155172413793, 27149590454.368828);
        sampleDataPoints.put(529.2025862068965, 27740391140.36843);
        sampleDataPoints.put(533.1896551724138, 28334365419.264084);
        sampleDataPoints.put(537.1767241379312, 28931224672.15409);
        sampleDataPoints.put(541.1637931034484, 29530670176.266907);
        sampleDataPoints.put(545.1508620689656, 30132392984.054413);
        sampleDataPoints.put(549.1379310344828, 30736073802.28543);
        sampleDataPoints.put(553.125, 31341382871.139046);
        sampleDataPoints.put(557.1120689655172, 31947979843.298065);
        sampleDataPoints.put(561.0991379310344, 32555513663.04236);
        sampleDataPoints.put(565.0862068965517, 33163622445.342415);
        sampleDataPoints.put(569.0732758620691, 33771933354.952545);
        sampleDataPoints.put(573.0603448275863, 34380062485.5044);
        sampleDataPoints.put(577.0474137931035, 34987614738.60042);
        sampleDataPoints.put(581.0344827586207, 35594183702.90713);
        sampleDataPoints.put(585.0215517241379, 36199351533.24868);
        sampleDataPoints.put(589.0086206896551, 36802688829.70009);
        sampleDataPoints.put(592.9956896551723, 37403754516.6808);
        sampleDataPoints.put(596.9827586206897, 38002095722.04802);
        sampleDataPoints.put(600.969827586207, 38597247656.19008);
        sampleDataPoints.put(604.9568965517242, 39188733491.11992);
        sampleDataPoints.put(608.9439655172414, 39776064239.56847);
        sampleDataPoints.put(612.9310344827586, 40358738634.07805);
        sampleDataPoints.put(616.9181034482758, 40936243006.09584);
        sampleDataPoints.put(620.905172413793, 41508051165.06714);
        sampleDataPoints.put(624.8922413793102, 42073624277.528854);
        sampleDataPoints.put(628.8793103448276, 42632410746.203);
        sampleDataPoints.put(632.8663793103449, 43183846089.09001);
        sampleDataPoints.put(636.8534482758621, 43727352818.56204);
        sampleDataPoints.put(640.8405172413793, 44262340320.45657);
        sampleDataPoints.put(644.8275862068966, 44788204733.16977);
        sampleDataPoints.put(646.8211206896553, 45047523422.23053);
        sampleDataPoints.put(648.814655172414, 45304328826.74974);
        sampleDataPoints.put(650.8081896551726, 45558541684.342804);
        sampleDataPoints.put(652.8017241379312, 45810081881.9901);
        sampleDataPoints.put(654.7952586206898, 46058868452.25856);
        sampleDataPoints.put(656.7887931034484, 46304819569.52341);
        sampleDataPoints.put(658.782327586207, 46547852546.18974);
        sampleDataPoints.put(660.7758620689656, 46787883828.91434);
        sampleDataPoints.put(662.7693965517242, 47024828994.82724);
        sampleDataPoints.put(664.7629310344828, 47258602747.75339);
        sampleDataPoints.put(666.7564655172414, 47489118914.43443);
        sampleDataPoints.put(668.75, 47716290440.75012);
        sampleDataPoints.put(670.7435344827586, 47940029387.94035);
        sampleDataPoints.put(672.7370689655172, 48160246928.82648);
        sampleDataPoints.put(674.7306034482758, 48376853344.033295);
        sampleDataPoints.put(676.7241379310345, 48589758018.21042);
        sampleDataPoints.put(678.7176724137933, 48798869436.25418);
        sampleDataPoints.put(680.7112068965519, 49004095179.529144);
        sampleDataPoints.put(682.7047413793105, 49205341922.089966);
        sampleDataPoints.put(684.6982758620691, 49402515426.90268);
        sampleDataPoints.put(686.6918103448277, 49595520542.0668);
        sampleDataPoints.put(688.6853448275863, 49784261197.036865);
        sampleDataPoints.put(690.6788793103449, 49968640398.84396);
        sampleDataPoints.put(692.6724137931035, 50148560228.317474);
        sampleDataPoints.put(694.6659482758621, 50323921836.30679);
        sampleDataPoints.put(696.6594827586207, 50494625439.902954);
        sampleDataPoints.put(698.6530172413793, 50660570318.66022);
        sampleDataPoints.put(700.6465517241379, 50821654810.818085);
        sampleDataPoints.put(702.6400862068965, 50977776309.52237);
        sampleDataPoints.put(704.6336206896551, 51128831259.047485);
        sampleDataPoints.put(706.6271551724137, 51274715151.01767);
        sampleDataPoints.put(708.6206896551724, 51415322520.62891);
        sampleDataPoints.put(710.6142241379312, 51550546942.87045);
        sampleDataPoints.put(712.6077586206898, 51680281028.74664);
        sampleDataPoints.put(714.6012931034484, 51804416421.49835);
        sampleDataPoints.put(716.594827586207, 51922843792.82489);
        sampleDataPoints.put(718.5883620689656, 52035452839.10565);
        sampleDataPoints.put(720.5818965517242, 52142132277.62137);
        sampleDataPoints.put(722.5754310344828, 52242769842.77652);
        sampleDataPoints.put(724.5689655172414, 52337252282.320435);
        sampleDataPoints.put(726.5625, 52425465353.569);
        sampleDataPoints.put(728.5560344827586, 52507293819.62662);
        sampleDataPoints.put(730.5495689655172, 52582621445.607635);
        sampleDataPoints.put(732.5431034482758, 52651330994.858185);
        sampleDataPoints.put(734.5366379310344, 52713304225.17752);
        sampleDataPoints.put(736.530172413793, 52768421885.04019);
        sampleDataPoints.put(738.5237068965516, 52816563709.81726);
        sampleDataPoints.put(740.5172413793103, 52857608417.99832);
        sampleDataPoints.put(741.5140086206898, 52875431173.90985);
        sampleDataPoints.put(742.5107758620691, 52891433707.41272);
        sampleDataPoints.put(743.5075431034484, 52905600570.637695);
        sampleDataPoints.put(744.5043103448277, 52917916251.45181);
        sampleDataPoints.put(745.501077586207, 52928365173.3407);
        sampleDataPoints.put(746.4978448275863, 52936931695.29016);
        sampleDataPoints.put(747.4946120689656, 52943600111.668335);
        sampleDataPoints.put(748.4913793103449, 52948354652.10739);
        sampleDataPoints.put(748.9897629310345, 52950009272.87964);
        sampleDataPoints.put(749.4881465517242, 52951179481.38574);
        sampleDataPoints.put(749.9865301724138, 52951863288.070404);
        sampleDataPoints.put(750.4849137931035, 52952058699.309875);
        sampleDataPoints.put(750.9832974137931, 52951763717.40863);
        sampleDataPoints.put(751.4816810344828, 52950976340.59592);
        sampleDataPoints.put(751.9800646551724, 52949694563.02142);
        sampleDataPoints.put(752.4784482758621, 52947916374.7522);
        sampleDataPoints.put(753.4752155172414, 52942862705.960724);
        sampleDataPoints.put(754.4719827586207, 52935799172.95929);
        sampleDataPoints.put(755.46875, 52926709548.92322);
        sampleDataPoints.put(756.4655172413793, 52915577541.3475);
        sampleDataPoints.put(757.4622844827586, 52902386791.92868);
        sampleDataPoints.put(758.4590517241379, 52887120876.44681);
        sampleDataPoints.put(759.4558189655172, 52869763304.64694);
        sampleDataPoints.put(760.4525862068965, 52850297520.12195);
        sampleDataPoints.put(761.4493534482758, 52828706900.19354);
        sampleDataPoints.put(762.4461206896551, 52804974755.79468);
        sampleDataPoints.put(763.4428879310344, 52779084331.35147);
        sampleDataPoints.put(764.4396551724137, 52751018804.66501);
        sampleDataPoints.put(765.436422413793, 52720761286.793335);
        sampleDataPoints.put(766.4331896551723, 52688294821.933075);
        sampleDataPoints.put(767.4299568965516, 52653602387.30209);
        sampleDataPoints.put(768.4267241379309, 52616666893.02063);
        sampleDataPoints.put(769.4234913793102, 52577471181.99365);
        sampleDataPoints.put(770.4202586206895, 52535998029.79288);
        sampleDataPoints.put(771.4170258620688, 52492230144.538086);
        sampleDataPoints.put(772.4137931034483, 52446150166.78003);
        sampleDataPoints.put(774.407327586207, 52346984157.39917);
        sampleDataPoints.put(776.4008620689656, 52238359770.17578);
        sampleDataPoints.put(778.3943965517242, 52120135684.96515);
        sampleDataPoints.put(780.3879310344828, 51992169489.17474);
        sampleDataPoints.put(782.3814655172414, 51854317673.98505);
        sampleDataPoints.put(784.375, 51706435630.57184);
        sampleDataPoints.put(786.3685344827586, 51548377646.327576);
        sampleDataPoints.put(788.3620689655173, 51379996901.08307);
        sampleDataPoints.put(790.355603448276, 51201145463.32947);
        sampleDataPoints.put(792.3491379310346, 51011674286.43933);
        sampleDataPoints.put(794.3426724137933, 50811433204.888916);
        sampleDataPoints.put(796.3362068965519, 50600270930.47943);
        sampleDataPoints.put(798.3297413793105, 50378035048.55878);
        sampleDataPoints.put(800.3232758620691, 50144572014.24365);
        sampleDataPoints.put(802.3168103448277, 49899727148.64044);
        sampleDataPoints.put(804.3103448275863, 49643344635.067444);
        sampleDataPoints.put(806.3038793103449, 49375267515.27649);
        sampleDataPoints.put(808.2974137931035, 49095337685.674255);
        sampleDataPoints.put(810.2909482758621, 48803395893.54474);
        sampleDataPoints.put(812.2844827586207, 48499281733.26965);
        sampleDataPoints.put(814.2780172413793, 48182833642.55133);
        sampleDataPoints.put(816.2715517241379, 47853888898.63367);
        sampleDataPoints.put(818.2650862068965, 47512283614.52435);
        sampleDataPoints.put(820.2586206896552, 47157852735.21564);
        sampleDataPoints.put(822.252155172414, 46790430033.90698);
        sampleDataPoints.put(824.2456896551726, 46409848108.2262);
        sampleDataPoints.put(826.2392241379312, 46015938376.45123);
        sampleDataPoints.put(828.2327586206898, 45608531073.73181);
        sampleDataPoints.put(830.2262931034484, 45187455248.310974);
        sampleDataPoints.put(832.219827586207, 44752538757.747314);
        sampleDataPoints.put(834.2133620689656, 44303608265.1358);
        sampleDataPoints.put(836.2068965517242, 43840489235.33002);
        sampleDataPoints.put(838.2004310344828, 43363005931.16376);
        sampleDataPoints.put(840.1939655172414, 42870981409.672485);
        sampleDataPoints.put(842.1875, 42364237518.31537);
        sampleDataPoints.put(844.1810344827586, 41842594891.19653);
        sampleDataPoints.put(846.1745689655172, 41305872945.28705);
        sampleDataPoints.put(848.1681034482758, 40753889876.64618);
        sampleDataPoints.put(850.1616379310344, 40186462656.6438);
        sampleDataPoints.put(852.1551724137931, 39603407028.181335);
        sampleDataPoints.put(854.1487068965519, 39004537501.913574);
        sampleDataPoints.put(856.1422413793105, 38389667352.470825);
        sampleDataPoints.put(858.1357758620691, 37758608614.680115);
        sampleDataPoints.put(860.1293103448277, 37111172079.78668);
        sampleDataPoints.put(862.1228448275863, 36447167291.67621);
        sampleDataPoints.put(864.1163793103449, 35766402543.09619);
        sampleDataPoints.put(866.1099137931035, 35068684871.877686);
        sampleDataPoints.put(868.1034482758621, 34353820057.156555);
        sampleDataPoints.put(870.0969827586207, 33621612615.59619);
        sampleDataPoints.put(872.0905172413793, 32871865797.607788);
        sampleDataPoints.put(874.0840517241379, 32104381583.57312);
        sampleDataPoints.put(876.0775862068965, 31318960680.065735);
        sampleDataPoints.put(878.0711206896551, 30515402516.072693);
        sampleDataPoints.put(880.0646551724137, 29693505239.216125);
        sampleDataPoints.put(882.0581896551723, 28853065711.975403);
        sampleDataPoints.put(884.051724137931, 27993879507.90802);
        sampleDataPoints.put(886.0452586206898, 27115740907.871643);
        sampleDataPoints.put(888.0387931034484, 26218442896.246094);
        sampleDataPoints.put(890.032327586207, 25301777157.154663);
        sampleDataPoints.put(892.0258620689656, 24365534070.686035);
        sampleDataPoints.put(894.0193965517242, 23409502709.11487);
        sampleDataPoints.put(896.0129310344828, 22433470833.12561);
        sampleDataPoints.put(898.0064655172414, 21437224888.03186);
        sampleDataPoints.put(900.0, 20420550000.0);
    }

    /**
     * Don't Need : method to print out the hash map from the data file
     * @param numLines
     * @param printDetails 
     */

    public void printHashMap(int numLines, boolean printDetails){
        
        if(printDetails){
            System.out.println("Data file is 578 lines long");
        }
        for(int i = 1; i<numLines; i++){
            
            String tmp = readFile(i);
            String[] bothNums = tmp.split("\\s+");
            //System.out.println(Arrays.toString(bothNums));
            if(printDetails){
                System.out.println("sampleDataPoints.put(" + bothNums[0] + " , " + bothNums[1] + ");") ;
                
            }
        }
        
       
       
    }
    
    /**
     * Don't NEED : Buffered reader to read a file.  
     * @param numLines
     * @return 
     */
  
    private String readFile(int numLines){
        
        String line = "";
        
        try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("C:/Users/Gia/Desktop/IntelliSysAss2/datfile.dat"));
 
                        int i = 0;
                        
			while ((sCurrentLine = br.readLine()) != null) {
                            
                                if(i < numLines){
                                    line = sCurrentLine;
                                    i++;
                                }
			}
 
		} catch (IOException e) {
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
			}
		}
        
        return line;
    }
    
}
