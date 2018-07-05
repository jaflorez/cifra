/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author jaflorez
 */
public class ViasUtil implements Serializable {

    private List<SelectItem> vias;

    public ViasUtil() {

        vias = new ArrayList<SelectItem>();
        vias.add(new SelectItem("0101", "0101:Circunvalación de la Isla de San Andrés"));
        vias.add(new SelectItem("0301", "0301:Circunvalación de la Isla de Providencia"));
        vias.add(new SelectItem("0501", "0501:La Espriella - Río Mataje"));
        vias.add(new SelectItem("1003", "1003:La Piscicultura - El Pepino"));
        vias.add(new SelectItem("1003-R1", "1003-R1:Pasto - La Piscicultura"));
        vias.add(new SelectItem("1702", "1702:Túquerres - Samaniego"));
        vias.add(new SelectItem("1901", "1901:Cali - Cruce Ruta 40 (Loboguerrero)"));
        vias.add(new SelectItem("2001", "2001:Munchique - Popayán"));
        vias.add(new SelectItem("2003", "2003:Altamira -El Caraño"));
        vias.add(new SelectItem("2003A", "2003A:Orrapihuasi - Depresión El Vergel"));
        vias.add(new SelectItem("2103", "2103:Cereté - Lorica"));
        vias.add(new SelectItem("2302", "2302:Ansermanuevo - La Virginia"));
        vias.add(new SelectItem("24HL01", "24HL01:Puerto Nolasco - Nátaga"));
        vias.add(new SelectItem("24HL02", "24HL02:Acceso a Itaibe (Cruce Ruta 24 - Itaibe)"));
        vias.add(new SelectItem("24HL03", "24HL03:Cruce Ruta 24 - Tesalia"));
        vias.add(new SelectItem("2501A", "2501A:Higuerones - Mojarras"));
        vias.add(new SelectItem("2501B", "2501B:Cebadal - Santa Bárbara"));
        vias.add(new SelectItem("2501B-R1", "2501B-R1:Cebadal -Sandoná - Consacá - Pasto"));
        vias.add(new SelectItem("2503", "2503:Mojarras - Popayán"));
        vias.add(new SelectItem("2510", "2510:Don Matías - Los Llanos"));
        vias.add(new SelectItem("2511", "2511:Los Llanos - Tarazá"));
        vias.add(new SelectItem("2512", "2512:Tarazá - Caucasia"));
        vias.add(new SelectItem("2514", "2514:Planeta Rica - La Ye"));
        vias.add(new SelectItem("25CC02", "25CC02:Timbío - El Tablón"));
        vias.add(new SelectItem("25CC15", "25CC15:Rosas - La Sierra - La Vega - San Sebastián - Santiago"));
        vias.add(new SelectItem("25CC15-R1", "25CC15-R1:Santiago - Santa Rosa"));
        vias.add(new SelectItem("25CCB", "25CCB:Variante de Popayán"));
        vias.add(new SelectItem("25NR01", "25NR01:Ipiales - Las Lajas"));
        vias.add(new SelectItem("25NRB", "25NRB:Variante de Daza"));
        vias.add(new SelectItem("25RSA", "25RSA:Paso Nacional por La Virginia"));
        vias.add(new SelectItem("25VL07", "25VL07:Cartago - Alcalá"));
        vias.add(new SelectItem("2601", "2601:Morales - Piendamó"));
        vias.add(new SelectItem("2602A", "2602A:Piendamó - Totoró"));
        vias.add(new SelectItem("26CC03-2", "26CC03-2:Tierracruz - Naranjal"));
        vias.add(new SelectItem("26CC04", "26CC04:Cruce Tramo 2602 - San Andrés de Pisimbalá"));
        vias.add(new SelectItem("26CC07", "26CC07:Inzá - Pedregal - Juntas"));
        vias.add(new SelectItem("2701", "2701:Plato - Salamina"));
        vias.add(new SelectItem("2702", "2702:Salamina - Palermo"));
        vias.add(new SelectItem("2901B", "2901B:Armenia - Montenegro - Alcalá"));
        vias.add(new SelectItem("2902", "2902:Chinchiná - Estación Uribe"));
        vias.add(new SelectItem("2902-R", "2902-R:Puente Mosquera - Cruce Avenida del Ferrocarril"));
        vias.add(new SelectItem("29RSA", "29RSA:Solución Vial Pereira - Dosquebradas"));
        vias.add(new SelectItem("29RSB", "29RSB:Variante de Galicia"));
        vias.add(new SelectItem("29VLA", "29VLA:Variante de Alcalá y Accesos"));
        vias.add(new SelectItem("3001", "3001:Neiva - Balsillas"));
        vias.add(new SelectItem("3002", "3002:Balsillas - Santo Domingo"));
        vias.add(new SelectItem("3002-R1", "3002-R1:Santo Domingo - Mina Blanca"));
        vias.add(new SelectItem("3105", "3105:Río Desbaratado - Palmira"));
        vias.add(new SelectItem("3105-R1", "3105-R1:Santander de Quilichao - Río Desbaratado"));
        vias.add(new SelectItem("3602", "3602:Chaparral - Ortega"));
        vias.add(new SelectItem("3603", "3603:Ortega - Guamo"));
        vias.add(new SelectItem("3701", "3701:La Plata - Puerto Valencia"));
        vias.add(new SelectItem("3701-R1", "3701-R1:Puerto Valencia - Guadualejo"));
        vias.add(new SelectItem("3702", "3702:Guadualejo - Irlanda"));
        vias.add(new SelectItem("3702-R1", "3702-R1:Puente Río Negro - El Palo"));
        vias.add(new SelectItem("37CCA", "37CCA:Rionegro - Tacueyó"));
        vias.add(new SelectItem("4001", "4001:Buenaventura - Cruce Ruta 40 (Loboguerrero)"));
        vias.add(new SelectItem("4002", "4002:Club Campestre - Armenia"));
        vias.add(new SelectItem("4003", "4003:Armenia - Cajamarca"));
        vias.add(new SelectItem("4003-R1", "4003-R1:Puente Cajamarca"));
        vias.add(new SelectItem("40CN01", "40CN01:El Portal - El Antojo"));
        vias.add(new SelectItem("40CNA", "40CNA:Paso por Chipaque"));
        vias.add(new SelectItem("40CNB", "40CNB:Puente Real - Cáqueza - El Tablón"));
        vias.add(new SelectItem("40MT01", "40MT01:Ramal Cruce Ruta 40 - El Porvenir"));
        vias.add(new SelectItem("40QN04", "40QN04:La Española - Armenia"));
        vias.add(new SelectItem("40QN05", "40QN05:Calarcá - La Cabaña"));
        vias.add(new SelectItem("40TL05", "40TL05:Cruce Ruta 40 - La Tambora"));
        vias.add(new SelectItem("4313", "4313:El Banco - San José"));
        vias.add(new SelectItem("4313-R1", "4313-R1:San José - Ye de Arjona"));
        vias.add(new SelectItem("43CS02", "43CS02:Ye de Arjona - Cuatrovientos"));
        vias.add(new SelectItem("4501", "4501:Puente Internacional San Miguel - Santa Ana"));
        vias.add(new SelectItem("4502", "4502:Villagarzón - Mocoa"));
        vias.add(new SelectItem("4503", "4503:Paso Nacional por Mocoa"));
        vias.add(new SelectItem("4503", "4503:Paso Nacional por Pitalito"));
        vias.add(new SelectItem("4504", "4504:Pitalito - Garzón"));
        vias.add(new SelectItem("4505", "4505:Paso Nacional por Garzón"));
        vias.add(new SelectItem("4506", "4506:Paso Nacional por Natagaima"));
        vias.add(new SelectItem("4507", "4507:Paso Nacional por El Espinal"));
        vias.add(new SelectItem("4510", "4510:Puente Puerto Salgar"));
        vias.add(new SelectItem("4518", "4518:Paso Nacional por Aracataca"));
        vias.add(new SelectItem("4518-R1", "4518-R1:Ye de Ciénaga - Intersección Ruta 90"));
        vias.add(new SelectItem("45A04", "45A04:Paso Nacional por Zipaquirá"));
        vias.add(new SelectItem("45A08", "45A08:Río Negro - San Alberto"));
        vias.add(new SelectItem("45AST08", "45AST08:Floridablanca - Palenque"));
        vias.add(new SelectItem("45HL01", "45HL01:Hobo - Yaguará (Inspección de Letrán)"));
        vias.add(new SelectItem("4803", "4803:Ansermanuevo - Cartago"));
        vias.add(new SelectItem("49GJB", "49GJB:Paso por San Juan del Cesar"));
        vias.add(new SelectItem("5001", "5001:Río Pató (El Afirmado) - La Ye (Las Animas)"));
        vias.add(new SelectItem("5005", "5005:Estación Uribe - Puente La Libertad"));
        vias.add(new SelectItem("5006", "5006:Puente La Libertad - Fresno"));
        vias.add(new SelectItem("5007", "5007:Fresno - Honda"));
        vias.add(new SelectItem("5008", "5008:Río Seco - Villeta"));
        vias.add(new SelectItem("5008A", "5008A:Paso Nacional por Madrid"));
        vias.add(new SelectItem("5008B", "5008B:Salida Túnel de la Abuela - Cruce Quebrada Negra"));
        vias.add(new SelectItem("5009", "5009:Bogotá (Los Patios) - Guasca"));
        vias.add(new SelectItem("50CN01", "50CN01:Caparrapí - La Aguada"));
        vias.add(new SelectItem("50CN03", "50CN03:Cruce Ruta 50 (El Salitre) - Cruce Ruta 55 (Briceño)"));
        vias.add(new SelectItem("50RS01", "50RS01:Apía - La Virginia"));
        vias.add(new SelectItem("5503", "5503:Duitama - La Palmera"));
        vias.add(new SelectItem("5504", "5504:La Palmera - Presidente"));
        vias.add(new SelectItem("5505", "5505:Presidente - Pamplona"));
        vias.add(new SelectItem("5507", "5507:Nueva Terminal de Cúcutá - Puente Internacional"));
        vias.add(new SelectItem("55BY11", "55BY11:Limites Departamento de Boyacá - Onzaga"));
        vias.add(new SelectItem("55NS08", "55NS08:San Cayetano - Cornejo"));
        vias.add(new SelectItem("55NS09", "55NS09:Cúcuta (Redoma el Salado) - La China"));
        vias.add(new SelectItem("55NSA", "55NSA:Glorieta K8 - Redoma el Salado"));
        vias.add(new SelectItem("5601", "5601:La Unión - Sonsón"));
        vias.add(new SelectItem("5604", "5604:Yacopí - La Palma"));
        vias.add(new SelectItem("6002", "6002:Quibdó - La Mansa"));
        vias.add(new SelectItem("6003", "6003:La Mansa - Bolombolo"));
        vias.add(new SelectItem("6006", "6006:Cruce Ruta 45 (Dos y Medio) - Otanche"));
        vias.add(new SelectItem("6007", "6007:Otanche - Ciquinquirá"));
        vias.add(new SelectItem("6008", "6008:Chiquinquirá - Tunja"));
        vias.add(new SelectItem("6009", "6009:Tunja - Páez"));
        vias.add(new SelectItem("6205", "6205:Hatillo - Cisneros"));
        vias.add(new SelectItem("6206", "6206:Cisneros - Puerto Berrío - Cruce Ruta 45"));
        vias.add(new SelectItem("6207", "6207:Cruce Puerto Araujo - Landázuri"));
        vias.add(new SelectItem("6208", "6208:Landázuri - Barbosa"));
        vias.add(new SelectItem("6209", "6209:Barbosa - Tunja"));
        vias.add(new SelectItem("6211", "6211:Sogamoso - Aguazul"));
        vias.add(new SelectItem("62BY05", "62BY05:Paso Nacional por Sogamoso"));
        vias.add(new SelectItem("62STA", "62STA:Variante de Barbosa"));
        vias.add(new SelectItem("6402", "6402:Cruce a Guane - San Gil"));
        vias.add(new SelectItem("6404", "6404:Belen - Sácama"));
        vias.add(new SelectItem("6405", "6405:Sácama - Cruce Ruta 45 (La Cabuya)"));
        vias.add(new SelectItem("6501", "6501:Villagarzón - Puerto Bello"));
        vias.add(new SelectItem("6502", "6502:San José del Fragua - Florencia"));
        vias.add(new SelectItem("6503", "6503:Florencia - Puerto Rico"));
        vias.add(new SelectItem("6507", "6507:San José del Guaviare - Cruce Puerto Rico"));
        vias.add(new SelectItem("6508", "6508:Cruce Puerto Rico - Ye de Granada"));
        vias.add(new SelectItem("6509", "6509:Paso Antiguo por el Río Guayuriba"));
        vias.add(new SelectItem("6512", "6512:Aguazul-Yopal"));
        vias.add(new SelectItem("6513", "6513:Yopal - Paz de Ariporo"));
        vias.add(new SelectItem("6514", "6514:Paz de Ariporo - La Cabuya"));
        vias.add(new SelectItem("6515", "6515:La Cabuya - Saravena"));
        vias.add(new SelectItem("65AMTA", "65AMTA:Paso por Puerto Caldas"));
        vias.add(new SelectItem("65AMTB", "65AMTB:Accesos al Puente Guillermo León Valencia"));
        vias.add(new SelectItem("65MTA", "65MTA:Paso por Puerto Lleras"));
        vias.add(new SelectItem("65MTB", "65MTB:Paso por Puerto Limón"));
        vias.add(new SelectItem("65MTD", "65MTD:Paso por Puerto Aljure"));
        vias.add(new SelectItem("65MTE", "65MTE:Puente Guatiquia - Los Caballos"));
        vias.add(new SelectItem("6602", "6602:Cruce Ruta 45 (La Fortuna) - Lebrija"));
        vias.add(new SelectItem("6603", "6603:Bucaramanga - Pamplona"));
        vias.add(new SelectItem("6605", "6605:Tame - Corocoro"));
        vias.add(new SelectItem("6606", "6606:Corocoro - Arauca"));
        vias.add(new SelectItem("7007", "7007:Río de Oro - Ocaña"));
        vias.add(new SelectItem("7008", "7008:Ocaña - Sardinata"));
        vias.add(new SelectItem("7009", "7009:Sardinata - El Zulia"));
        vias.add(new SelectItem("70NS01", "70NS01:La Ondina - Convención"));
        vias.add(new SelectItem("74CR02", "74CR02:Santa Lucía - Moñitos"));
        vias.add(new SelectItem("7506", "7506:Calamar - San José del Guaviare"));
        vias.add(new SelectItem("7801", "7801:Lorica - Chinú"));
        vias.add(new SelectItem("7802", "7802:Puerta de Hierro - Magangué - Yatí"));
        vias.add(new SelectItem("7806", "7806:Tamalameque - El Burro"));
        vias.add(new SelectItem("8501", "8501:Leticia - Tarapacá"));
        vias.add(new SelectItem("8801", "8801:Cuestecitas - Paradero"));
        vias.add(new SelectItem("88GJ02", "88GJ02:Acceso a Albania"));
        vias.add(new SelectItem("9003", "9003:Moñitos - Lorica"));
        vias.add(new SelectItem("9004", "9004:Lorica - San Onofre"));
        vias.add(new SelectItem("9005", "9005:María la Baja - Cruz del Viso"));
        vias.add(new SelectItem("9006", "9006:Puente de la Cordialidad"));
        vias.add(new SelectItem("9007", "9007:Accesos y Puente Laureano Gómez"));
        vias.add(new SelectItem("90SC02", "90SC02:Coveñas - Sabaneta"));

    }

    public List<SelectItem> getVias() {
        return vias;
    }

    public void setVias(List<SelectItem> vias) {
        this.vias = vias;
    }

}
