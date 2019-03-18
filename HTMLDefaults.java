
package javaapplication58;

/**Класс, содержайщий шаблоны всех HTML-страниц сайта, доступных для редактирования
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class HTMLDefaults {

private HTMLDefaults() {}

/**Текст по умолчанию для страницы набора, если набор открыт*/
public static final String NOOBIES_WANTED = " Наша команда всегда рада принять в свои ряды достойных бойцов!\n" +
"\n" +
"Мы хотим видеть в своих рядах активных, адекватных, спортивных молодых людей, славян, в возрасте от \n" +
"20-ти лет. Бойцов, готовых посещать командные мероприятия минимум 2 раза в месяц. Способных в ближайшие \n" +
"сроки купить минимально-необходимый комплект снаряжения.\n" +
"\n" +
"Большинство бойцов проживает на Юго-Западе Санкт-Петербурга. Средний возраст бойцов в команде - 30 лет.\n" +
"\n" +
"Мы хотим собрать единомышленников не только для выезда на игры, но и совместного досуга вне страйкбола: \n" +
"вместе отмечать праздники, ходить в спортзал и т.д. У команды есть свой полигон для тренировок и \n" +
"проведения игр.\n" +
"\n" +
"     Если вы хотите присоединиться к нашему дружному коллективу, то учтите, что:\n" +
"\n" +
"- В команде собираются ежеквартальные материальные взносы, в том числе и с кандидатов;\n" +
"- Мы выезжаем на игры и тренировки круглый год, а не только в теплый сезон;\n" +
"- Мы не боимся запачкаться с ног до головы и промокнуть до нитки; \n" +
"- Кандидаты в конце испытательного срока сдают обязательный экзамен по ОФП; \n" +
"- Команда - это семья! Это единый организм, а не сборище одиночек! У нас один за всех, и все за одного!\n" +
"\n" +
"- Еще раз ВНИМАТЕЛЬНО ознакомьтесь с правилами страйкбола, нашим Уставом , требованиями по снаряжению   \n" +
"  и вооружению. \n" +
"- Обратитесь к Старшине команды в ВК, он расскажет Вам, как действовать дальше, и ответит на все вопросы, \n" +
"  которых у вас наверняка накопилось довольно много:";
    
/**Текст по умолчанию для страницы набора, если набор закрыт*/
public static final String NOOBIES_NOT_WANTED = "К сожалению на данный момент набор закрыт, команда полностью укомплектована.";

/**Ссылка по умолчанию на ВК человека, ответственного за набор новичков*/
public static final String NOOBIES_CONTACT_LINK = "http://vk.com/mashyanov";
    
/**Шаблон страницы галереи сайта*/
 public static final String GALLERY = "<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"\n" +
"	\n" +
"\n" +
"	<link href=\"reset-min.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
"	<link href=\"layout.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
"    <link href=\"lightgallery/skins/default/style.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
" 	\n" +
"\n" +
" \n" +
"\n" +
"<script src=\"lightgallery/lightgallery.min.js\" type=\"text/javascript\"></script>\n" +
"	<script type=\"text/javascript\">\n" +
"	lightgallery.init();\n" +
"\n" +
"	var AJAX = (function(){\n" +
"\n" +
"	  var req = window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();\n" +
"\n" +
"	  return {\n" +
"\n" +
"		get : function(url, callback, context){\n" +
"		  req.open('GET', url);\n" +
"		  req.onreadystatechange = function() {\n" +
"		if(req.readyState == 4){\n" +
"			callback.call(context || window, req);\n" +
"		}\n" +
"		  };\n" +
"		  req.send(null);\n" +
"		}\n" +
"\n" +
"	  } \n" +
"\n" +
"	})();\n" +
"\n" +
"	var someObj = {a : 'test'};\n" +
"\n" +
"	function loadImages() {\n" +
"	  AJAX.get('load.html', function(req){\n" +
"		document.getElementById(\"moreImages\").innerHTML += req.responseText;\n" +
"		document.getElementById(\"load_new\").style.display = 'none';\n" +
"\n" +
"		lightgallery.init();\n" +
"	  }, someObj);\n" +
"	}\n" +
"	</script>\n" +
"\n" +
"<!-- Телефон -->\n" +
"<link href=\"tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\">\n" +
"</head>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"<style type=\"text/css\">\n" +
"</style>\n" +
"<title>Галерея</title>\n" +
"<body><table width=\"1900\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"\n" +
"  <tr>\n" +
"   <td colspan=\"2\" valign=\"top\"><img src=\"img/title-pc.png\" width=\"1193\" height=\"297\" usemap=\"#pkMap\" id=\"pk\"; border=\"0\">\n" +
"    <map name=\"pkMap\">\n" +
"   <area shape=\"poly\" coords=\"182,48,186,63,185,91,187,103,188,122,186,140,186,159,187,183,186,194,194,208,205,219,219,230,238,240,251,246,265,253,279,260,288,265,300,259,312,252,331,248,348,238,364,229,378,216,391,199,393,184,391,162,392,144,391,129,393,113,392,100,392,81,394,70,395,59,399,48,382,43,355,29,325,17,311,15,281,14,256,17,239,26,224,31,207,40\" href=\"main.html\">\n" +
"  <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"fighters.html\">\n" +
" \n" +
"    </map></td>\n" +
"  <tr>\n" +
"    <td width=\"472\" rowspan=\"2\" valign=\"top\"><p id=\"p\">&nbsp;</p>\n" +
"      \n" +
"     <img src=\"menu/menu6.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\"></td>\n" +
"     \n" +
"       <map name=\"menuMap\">\n" +
"  <area shape=\"rect\" coords=\"163,56,345,84\" href=\"fighters.html\">\n" +
"  <area shape=\"rect\" coords=\"167,105,275,132\" href=\"ustav.html\">\n" +
"  <area shape=\"rect\" coords=\"164,213,302,239\" href=\"guns.html\">\n" +
"  <area shape=\"rect\" coords=\"164,158,293,180\" href=\"snaryaga.html\">\n" +
"  <area shape=\"rect\" coords=\"165,307,381,332\" href=\"rules.html\">\n" +
"  <area shape=\"rect\" coords=\"164,352,384,375\" href=\"noobies.html\">\n" +
"  <area shape=\"rect\" coords=\"166,6,263,30\" href=\"main.html\">\n" +
"    </map>\n" +
"     \n" +
"    <td width=\"884\" valign=\"left\">\n" +
"      \n" +
"    \n" +
"      <p><img src=\"img/title-tel.png\" id=\"mobile\" align=\"center\" width=\"768\" height=\"150\"><img src=\"menu/zaglav/06.png\" width=\"825\" height=\"92\"></p>\n" +
"      <p>&nbsp;</p>\n" +
"       <p>\n" +
"	   !!!PHOTO\n" +
"      \n" +
"        </p>\n" +
"      </p>\n" +
"      <p>&nbsp;</p>\n" +
"      <p>&nbsp;</p>\n" +
"          \n" +
"	  !!!VIDEO\n" +
"    </a></p>\n" +
"   </td><td width=\"249\"> </td><td width=\"137\"></td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"   \n" +
"  </tr>\n" +
"</table>";

/**Шаблон ПК-версии страницы  кандидата*/
public static final String CANDIDATE ="<head>\n" +
"\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"\n" +
"\n" +
"	<link href=\"reset-min.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
"	<link href=\"layout.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
"\n" +
"    <link href=\"../lightgallery/skins/default/style.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
" 	\n" +
"\n" +
" \n" +
"\n" +
"<script src=\"../lightgallery/lightgallery.min.js\" type=\"text/javascript\"></script>\n" +
"	<script type=\"text/javascript\">\n" +
"	lightgallery.init();\n" +
"\n" +
"	var AJAX = (function(){\n" +
"\n" +
"	  var req = window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();\n" +
"\n" +
"	  return {\n" +
"\n" +
"		get : function(url, callback, context){\n" +
"		  req.open('GET', url);\n" +
"		  req.onreadystatechange = function() {\n" +
"		if(req.readyState == 4){\n" +
"			callback.call(context || window, req);\n" +
"		}\n" +
"		  };\n" +
"		  req.send(null);\n" +
"		}\n" +
"\n" +
"	  }\n" +
"\n" +
"	})();\n" +
"\n" +
"	var someObj = {a : 'test'};\n" +
"\n" +
"	function loadImages() {\n" +
"	  AJAX.get('load.html', function(req){\n" +
"		document.getElementById(\"moreImages\").innerHTML += req.responseText;\n" +
"		document.getElementById(\"load_new\").style.display = 'none';\n" +
"\n" +
"		lightgallery.init();\n" +
"	  }, someObj);\n" +
"	}\n" +
"	</script>\n" +
"<!-- Телефон -->\n" +
"<link href=\"../tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"../pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"../pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\"><link href=\"../text.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
"</head>\n" +
"\n" +
"\n" +
"<style type=\"text/css\">\n" +
"\n" +
"</style>\n" +
"\n" +
"<body>\n" +
"<table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"<tr>\n" +
"  <td width=\"26%\" rowspan=\"2\" align=\"center\" valign=\"top\"><table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"    <title>\n" +
"    !!!NAME \n" +
"    </title>\n" +
"    <tr>\n" +
"      <td colspan=\"2\"><img src=\"../img/title-pc.png\" width=\"1193\" height=\"297\" usemap=\"#pkMap\" id=\"pk\"; border=\"0\">\n" +
"        <map name=\"pkMap\">\n" +
"          <area shape=\"poly\" coords=\"186,53,190,68,189,96,191,108,192,127,190,145,190,164,191,188,190,199,198,213,209,224,223,235,242,245,255,251,269,258,283,265,292,270,304,264,316,257,335,253,352,243,368,234,382,221,395,204,397,189,395,167,396,149,395,134,397,118,396,105,396,86,398,75,399,64,403,53,386,48,359,34,329,22,315,20,285,19,260,22,243,31,228,36,211,45\" href=\"../main.html\">\n" +
"          <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"../fighters.html\">\n" +
"        </map></td>\n" +
"    <tr>\n" +
"      <td width=\"26%\" rowspan=\"2\" align=\"center\" valign=\"top\"><p id=\"p\">&nbsp;</p>\n" +
"        <img src=\"../menu/menu2.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\"></td>\n" +
"      <map name=\"menuMap\">\n" +
"        <area shape=\"rect\" coords=\"171,8,262,36\" href=\"../main.html\">\n" +
"        <area shape=\"rect\" coords=\"174,106,285,132\" href=\"../ustav.html\">\n" +
"        <area shape=\"rect\" coords=\"175,159,310,182\" href=\"../snaryaga.html\">\n" +
"        <area shape=\"rect\" coords=\"174,210,310,236\" href=\"../guns.html\">\n" +
"        <area shape=\"rect\" coords=\"173,259,266,283\" href=\"../gallery.html\">\n" +
"        <area shape=\"rect\" coords=\"173,307,396,334\" href=\"../rules.html\">\n" +
"        <area shape=\"rect\" coords=\"171,352,391,375\" href=\"../noobies.html\">\n" +
"        <area shape=\"rect\" coords=\"190,53,371,82\" href=\"../fighters.html\">\n" +
"      </map>\n" +
"      <td width=\"74%\" valign=\"top\"><p><br>\n" +
"         <span class=\"name\">\n" +
"         !!!NAME\n" +
"         </span></p>\n" +
"         <p><span class=\"text\"><a href=\"../fighters.html\">&lt;&lt; Назад</a></span>\n" +
"           <span class=\"text\"><font size=\"+1\"></font></span></p>\n" +
"         <p><span class=\"fighter\">\n" +
"         !!!POSITION\n" +
"          </span></p>\n" +
"         <p>\n" +
"           <a href=\"../img/Fighters/fullsize/!!!LINK.jpg\" rel=\"lightgallery\" title=\"!!!CALLSIGN\"><img src=\"../img/Fighters/!!!LINK.jpg\"  alt=\"\" width=\"500\"  border='2px solid #808000'/></a></p>\n" +
"         \n" +
"  </table></td>\n" +
"</tr>\n" +
"</table>";

/**Шаблон мобильной версии страницы  кандидата*/
public static final String CANDIDATE_MOB ="<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"<head>\n" +
"	<meta charset=\"utf-8\">\n" +
"	<title>СК \"БрОН\"</title>\n" +
"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"\n" +
"	<link rel=\"stylesheet\" href=\"../css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<link rel=\"stylesheet\" href=\"../css/font-awesome.css\" >\n" +
"\n" +
"	\n" +
"\n" +
"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\" type=\"text/javascript\" ></script>\n" +
"    <script src=\"../js/menu.js\" type=\"text/javascript\"></script> \n" +
"<script src=\"../scripts/jquery-1.8.3.min.js\" type=\"text/javascript\"></script>\n" +
"<script src=\"../scripts/jquery.cycle.all.min.js\" type=\"text/javascript\"></script>\n" +
"<script type=\"text/javascript\">\n" +
"$(document).ready(function() {\n" +
"$('.slideshow').cycle({\n" +
"fx: 'fade'\n" +
"});\n" +
"});\n" +
"</script>\n" +
"<style type=\"text/css\">\n" +
".slideshow {\n" +
"width: 299 px;\n" +
"height: 224 px;\n" +
"margin: auto;\n" +
"border: medium;\n" +
"border-color: #5E5D01; \n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"<body>\n" +
"		<h1 class=\"title\">СК \"БрОН\"</h1>\n" +
"<div class=\"topHeader\"></div>\n" +
"\n" +
"	<div class=\"mainWrap\">\n" +
"	<a id=\"touch-menu\" class=\"mobile-menu\" href=\"#\"><i class=\"icon-reorder\"></i>Меню</a>\n" +
"\n" +
"		<nav>\n" +
"			<ul class=\"menu\">\n" +
"				<li>\n" +
"					<a href=\"../main.html\"><i class=\"icon-home\"></i>Главная</a>\n" +
"					\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"../fighters.html\"><i class=\"icon-group\"></i>Бойцы Команды</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"../gallery.html\"><i class=\"icon-camera\"></i>Галерея</a>\n" +
"					\n" +
"			  </li>\n" +
"				<li>\n" +
"					<a  href=\"../guns.html\"><i class=\"icon-screenshot\"></i>Вооружение</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"../snaryaga.html\"><i class=\"icon-shield\"></i>Снаряжение</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"../noobies.html\"><i class=\"icon-user\"></i>Вступить в Команду</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"../docs.html\"><i class=\"icon-book\"></i>Документы</a>\n" +
"				</li>\n" +
"                \n" +
"		  </ul>\n" +
"		</nav>\n" +
"</div>\n" +
"        \n" +
"        <h3 class=\"title\">\n" +
"		!!!NAME\n" +
"		<br>\n" +
"!!!POSITION\n" +
"</h3>\n" +
"		<div align=\"center\" class=\"slideshow\" >\n" +
"<img src=\"../img/fighters/fullsize/!!!LINK.png\" alt=\"\" width=\"160\" height=\"120\" />\n" +
"\n" +
"        \n" +
"        </div>\n" +
"        \n" +
"        \n" +
"        \n" +
"        \n" +
"        \n" +
"<p>&nbsp;</p>\n" +
"<p><a href=\"../fighters.html\"><img src=\"../img/back.png\" width=\"160\" height=\"70\"></a>\n" +
"</p>\n" +
"<p>&nbsp;</p>\n" +
"</body>\n" +
"</html>";

/**Шаблон ПК-версии страницы полноправного члена команды*/
public static final String FIGHTER = "<head>\n" +
"\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"\n" +
"\n" +
"	<link href=\"reset-min.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
"	<link href=\"layout.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
"\n" +
"    <link href=\"../lightgallery/skins/default/style.css\" type=\"text/css\" media=\"screen\" rel=\"stylesheet\" />\n" +
" 	\n" +
"\n" +
" \n" +
"\n" +
"<script src=\"../lightgallery/lightgallery.min.js\" type=\"text/javascript\"></script>\n" +
"	<script type=\"text/javascript\">\n" +
"	lightgallery.init();\n" +
"\n" +
"	var AJAX = (function(){\n" +
"\n" +
"	  var req = window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();\n" +
"\n" +
"	  return {\n" +
"\n" +
"		get : function(url, callback, context){\n" +
"		  req.open('GET', url);\n" +
"		  req.onreadystatechange = function() {\n" +
"		if(req.readyState == 4){\n" +
"			callback.call(context || window, req);\n" +
"		}\n" +
"		  };\n" +
"		  req.send(null);\n" +
"		}\n" +
"\n" +
"	  }\n" +
"\n" +
"	})();\n" +
"\n" +
"	var someObj = {a : 'test'};\n" +
"\n" +
"	function loadImages() {\n" +
"	  AJAX.get('load.html', function(req){\n" +
"		document.getElementById(\"moreImages\").innerHTML += req.responseText;\n" +
"		document.getElementById(\"load_new\").style.display = 'none';\n" +
"\n" +
"		lightgallery.init();\n" +
"	  }, someObj);\n" +
"	}\n" +
"	</script>\n" +
"<!-- Телефон -->\n" +
"<link href=\"../tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"../pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"../pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\"><link href=\"../text.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
"</head>\n" +
"\n" +
"\n" +
"<style type=\"text/css\">\n" +
"\n" +
"</style>\n" +
"\n" +
"<body>\n" +
"<table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"<tr>\n" +
"  <td width=\"22%\" rowspan=\"2\" align=\"center\" valign=\"top\"><table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"    <title>\n" +
"    !!!NAME \n" +
"    </title>\n" +
"    <tr>\n" +
"      <td colspan=\"2\"><img src=\"../img/title-pc.png\" width=\"1193\" height=\"297\" usemap=\"#pkMap\" id=\"pk\"; border=\"0\">\n" +
"        <map name=\"pkMap\">\n" +
"          <area shape=\"poly\" coords=\"186,53,190,68,189,96,191,108,192,127,190,145,190,164,191,188,190,199,198,213,209,224,223,235,242,245,255,251,269,258,283,265,292,270,304,264,316,257,335,253,352,243,368,234,382,221,395,204,397,189,395,167,396,149,395,134,397,118,396,105,396,86,398,75,399,64,403,53,386,48,359,34,329,22,315,20,285,19,260,22,243,31,228,36,211,45\" href=\"../main.html\">\n" +
"          <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"../fighters.html\">\n" +
"        </map></td>\n" +
"    <tr>\n" +
"      <td width=\"26%\" rowspan=\"2\" align=\"center\" valign=\"top\"><p id=\"p\">&nbsp;</p>\n" +
"        <img src=\"../menu/menu2.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\"></td>\n" +
"      <map name=\"menuMap\">\n" +
"        <area shape=\"rect\" coords=\"171,8,262,36\" href=\"../main.html\">\n" +
"        <area shape=\"rect\" coords=\"174,106,285,132\" href=\"../ustav.html\">\n" +
"        <area shape=\"rect\" coords=\"175,159,310,182\" href=\"../snaryaga.html\">\n" +
"        <area shape=\"rect\" coords=\"174,210,310,236\" href=\"../guns.html\">\n" +
"        <area shape=\"rect\" coords=\"173,259,266,283\" href=\"../gallery.html\">\n" +
"        <area shape=\"rect\" coords=\"173,307,396,334\" href=\"../rules.html\">\n" +
"        <area shape=\"rect\" coords=\"171,352,391,375\" href=\"../noobies.html\">\n" +
"        <area shape=\"rect\" coords=\"190,53,371,82\" href=\"../fighters.html\">\n" +
"      </map>\n" +
"      <td width=\"74%\" valign=\"top\"><p><br>\n" +
"         <span class=\"name\">\n" +
"         !!!NAME\n" +
"         </span></p>\n" +
"         <p><span class=\"text\"><a href=\"../fighters.html\">&lt;&lt; Назад</a></span>\n" +
"           <span class=\"text\"><font size=\"+1\"></font></span></p>\n" +
"         <p><span class=\"fighter\">\n" +
"         !!!POSITION\n" +
"          </span></p>\n" +
"         <p>\n" +
"           <a href=\"../img/Fighters/fullsize/!!!LINK.jpg\" rel=\"lightgallery\" title=\"!!!CALLSIGN\"><img src=\"../img/Fighters/!!!LINK.jpg\"  alt=\"\" width=\"500\"  border='2px solid #808000'/></a></p>\n" +
"         <p><a href=\"../img/Fighters/fullsize/!!!LINK1.jpg\" rel=\"lightgallery[1]\" title=\"!!!CALLSIGN\"><img src=\"../img/Fighters/!!!LINK1.png\"  border='2px solid #808000' alt=\"\"/></a> <a href=\"../img/Fighters/fullsize/!!!LINK2.jpg\" rel=\"lightgallery[1]\" title=\"!!!CALLSIGN\"><img src=\"../img/Fighters/!!!LINK2.png\" border='2px solid #808000' alt=\"\"/></a> <a href=\"../img/Fighters/fullsize/!!!LINK3.jpg\" rel=\"lightgallery[1]\" title=\"!!!CALLSIGN\"><img src=\"../img/Fighters/!!!LINK3.png\" border='2px solid #808000' alt=\"\"/></a>\n" +
"           </pre>\n" +
"         </p>\n" +
"         <p>&nbsp;</p></td>\n" +
"    </tr>\n" +
"    <tr>\n" +
"      <td>&nbsp;</td>\n" +
"    </tr>\n" +
"  </table></td>\n" +
"</tr>\n" +
"</table>";

/**Шаблон мобильной версии страницы полноправного члена команды*/
public static final String FIGHTER_MOB = "<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"<head>\n" +
"	<meta charset=\"utf-8\">\n" +
"	<title>СК \"БрОН\"</title>\n" +
"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"\n" +
"	<link rel=\"stylesheet\" href=\"../css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<link rel=\"stylesheet\" href=\"../css/font-awesome.css\" >\n" +
"\n" +
"	\n" +
"\n" +
"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\" type=\"text/javascript\" ></script>\n" +
"    <script src=\"../js/menu.js\" type=\"text/javascript\"></script> \n" +
"<script src=\"../scripts/jquery-1.8.3.min.js\" type=\"text/javascript\"></script>\n" +
"<script src=\"../scripts/jquery.cycle.all.min.js\" type=\"text/javascript\"></script>\n" +
"<script type=\"text/javascript\">\n" +
"$(document).ready(function() {\n" +
"$('.slideshow').cycle({\n" +
"fx: 'fade'\n" +
"});\n" +
"});\n" +
"</script>\n" +
"<style type=\"text/css\">\n" +
".slideshow {\n" +
"width: 299 px;\n" +
"height: 224 px;\n" +
"margin: auto;\n" +
"border: medium;\n" +
"border-color: #5E5D01; \n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"<body>\n" +
"		<h1 class=\"title\">СК \"БрОН\"</h1>\n" +
"<div class=\"topHeader\"></div>\n" +
"\n" +
"	<div class=\"mainWrap\">\n" +
"	<a id=\"touch-menu\" class=\"mobile-menu\" href=\"#\"><i class=\"icon-reorder\"></i>Меню</a>\n" +
"\n" +
"		<nav>\n" +
"			<ul class=\"menu\">\n" +
"				<li>\n" +
"					<a href=\"../main.html\"><i class=\"icon-home\"></i>Главная</a>\n" +
"					\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"../fighters.html\"><i class=\"icon-group\"></i>Бойцы Команды</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"../gallery.html\"><i class=\"icon-camera\"></i>Галерея</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"../guns.html\"><i class=\"icon-screenshot\"></i>Вооружение</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"../snaryaga.html\"><i class=\"icon-shield\"></i>Снаряжение</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"../noobies.html\"><i class=\"icon-user\"></i>Вступить в Команду</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"../docs.html\"><i class=\"icon-book\"></i>Документы</a>\n" +
"				</li>\n" +
"                \n" +
"		  </ul>\n" +
"		</nav>\n" +
"</div>\n" +
"        \n" +
"        <h3 class=\"title\"><strong>\n" +
"		!!!NAME\n" +
"		</strong><br>\n" +
"!!!POSITION\n" +
"</h3>\n" +
"		<div align=\"center\" class=\"slideshow\" >\n" +
"<img src=\"../img/fighters/fullsize/!!!LINK.png\" alt=\"\" width=\"160\" height=\"120\" />\n" +
"<img src=\"../img/fighters/fullsize/!!!LINK1.png\" alt=\"\" width=\"160\" height=\"120\" />\n" +
"<img src=\"../img/fighters/fullsize/!!!LINK2.png\" alt=\"\" width=\"160\" height=\"120\" />\n" +
"<img src=\"../img/fighters/fullsize/!!!LINK3.png\" alt=\"\" width=\"160\" height=\"120\" />\n" +
"\n" +
"        \n" +
"        </div>\n" +
"        \n" +
"        \n" +
"        \n" +
"        \n" +
"        \n" +
"<p>&nbsp;</p>\n" +
"<p><a href=\"../fighters.html\"><img src=\"../img/back.png\" width=\"160\" height=\"70\"></a>\n" +
"</p>\n" +
"<p>&nbsp;</p>\n" +
"</body>\n" +
"</html>";

/**Шаблон ПК-версии главной страницы сайта*/
public static final String MAINPAGE ="<!doctype html>\n" +
"<html>\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"<title>СК &quot;БрОН&quot;</title>\n" +
"<script language=\"javascript\">\n" +
"\n" +
"</script>\n" +
"\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\" >\n" +
"<!-- Телефон -->\n" +
"<link href=\"tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\">\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
"</head>\n" +
"\n" +
"\n" +
"\n" +
"<body>\n" +
"\n" +
"<map name=\"Map\" id=\"Map\">\n" +
"  <area shape=\"rect\" coords=\"529,269,583,274\" href=\"#\">\n" +
"  <area shape=\"rect\" coords=\"2,6,7,8\" href=\"#\">\n" +
"</map>\n" +
"\n" +
"<table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"  <tr>\n" +
"    <td colspan=\"3\"><img src=\"img/title-pc.png\" width=\"1193\" height=\"297\" id=\"pk\" usemap=\"#pkMap\"; border=\"0\">\n" +
"    <map name=\"pkMap\">\n" +
"   \n" +
"  <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"fighters.html\">\n" +
" \n" +
"    </map></td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td width=\"28%\" height=\"465\" valign=\"top\"><p id=\"p\">&nbsp;</p>\n" +
"    \n" +
"    \n" +
"    <img src=\"menu/menu1.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\"></td>\n" +
"	\n" +
"    \n" +
"    <td width=\"46%\" align=\"center\" valign=\"top\"><p>&nbsp;</p>\n" +
"    <img src=\"img/title-tel.png\" id=\"mobile\" align=\"center\" width=\"768\" height=\"150\">\n" +
"      &nbsp;&nbsp;&nbsp;\n" +
"      <span class=\"text-main\"> Цель создания СК \"БрОН\" - объединить единомышленников для  организации\n" +
"          интересного, активного, экстремального отдыха,\n" +
"          приятного общения в хорошей компании как на  играх, так и\n" +
"          вне страйкбольных мероприятий.</p>\n" +
"      &nbsp;&nbsp;</span>      \n" +
"	  <span class=\"text-main\">&nbsp;&nbsp; Мы используем российское оружие и снаряжение, стоящее на\n" +
"        вооружении\n" +
"        ВС РФ, но  не занимаемся точным  моделированием.\n" +
"        При этом соблюдаем единообразие в форме одежды,  снаряжении и\n" +
"        вооружении.</p>\n" +
"		<span class=\"text-main\">&nbsp;&nbsp; С 21 июня 2017 команда официально состоит в блоке \"СОЮЗ\"\n" +
"		<a href=\"http://www.airsoftgunspb.ru/forum/index.php\" target=\"_blank\">Санкт-Петербургской Ассоциации Страйкбола (СПАС)</a>\n" +
".</p>\n" +
"      &nbsp;&nbsp;&nbsp;</span><span class=\"text-main\"> СК \"БрОН\" образована  07 июля 2012 года в Санкт-Петербурге\n" +
"      и на данный момент насчитывает <br>\n" +
"	  !!!F бойц!!!SF, !!!C кандидат!!!SC и !!!R резервист!!!SR. </span>\n" +
"      </p>\n" +
"      <p class=\"text\">&nbsp;</p>\n" +
"      <p class=\"text\">&nbsp;</p>\n" +
"      <p class=\"text-main\">&nbsp;</p>\n" +
"      <p class=\"text\">&nbsp;</p>\n" +
"      <p class=\"text\">&nbsp;</p>\n" +
"      <p class=\"disclaimer\">Все фото и видеоматериалы взяты из открытых источников либо из личных архивов команды. <br>\n" +
"      Все упоминания про \"оружие\", \"гранаты\", \"гранатомёты\" и \"гильзы\" на данном сайте не имеют никакого отношения к настоящим боеприпасам и используются только для условного обозначения. &quot;Убитые&quot; и &quot;раненные&quot; так же всего лишь игровые обозначения. Все изделия, описанные на данном сайте в разделе \"Вооружение\", имеют исключительно игровое назначение для использования в страйкболе, сертифицированы или содержат сертифицированную на территории РФ пиротехнику и разрешены к свободной продаже. Применение данных изделий возможно только при строгом соблюдении правил техники безопасности, описанных в инструкции по эксплуатации изделий.</p>\n" +
" </td>\n" +
"    <td width=\"26%\" align=\"left\" valign=\"top\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"3\" valign=\"top\">&nbsp;</td>\n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"</map>\n" +
"\n" +
"<map name=\"menuMap\">\n" +
"  <area shape=\"rect\" coords=\"173,56,355,83\" href=\"fighters.html\">\n" +
"  <area shape=\"rect\" coords=\"174,106,285,132\" href=\"ustav.html\">\n" +
"  <area shape=\"rect\" coords=\"175,159,310,182\" href=\"snaryaga.html\">\n" +
"  <area shape=\"rect\" coords=\"174,210,310,236\" href=\"guns.html\">\n" +
"  <area shape=\"rect\" coords=\"173,259,266,283\" href=\"gallery.html\">\n" +
"  <area shape=\"rect\" coords=\"173,307,396,334\" href=\"rules.html\">\n" +
"  <area shape=\"rect\" coords=\"171,352,391,375\" href=\"noobies.html\">\n" +
"</map>\n" +
"</body>\n" +
"</html>";

/**Шаблон мобильной версии главной страницы сайта*/
public static final String MAINPAGE_MOB ="<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"<head>\n" +
"	<meta charset=\"utf-8\">\n" +
"	<title>СК \"БрОН\"</title>\n" +
"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"\n" +
"	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<link rel=\"stylesheet\" href=\"css/font-awesome.css\" >\n" +
"\n" +
"	\n" +
"\n" +
"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\" type=\"text/javascript\" ></script>\n" +
"    <script src=\"js/menu.js\" type=\"text/javascript\"></script> \n" +
"\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"		<h1 class=\"title\">СК \"БрОН\"</h1>\n" +
"<div class=\"topHeader\"></div>\n" +
"\n" +
"	<div class=\"mainWrap\">\n" +
"	<a id=\"touch-menu\" class=\"mobile-menu\" href=\"#\"><i class=\"icon-reorder\"></i>Меню</a>\n" +
"\n" +
"		<nav>\n" +
"			<ul class=\"menu\">\n" +
"				<li>\n" +
"					<a href=\"main.html\"><i class=\"icon-home\"></i>Главная</a>\n" +
"					\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"fighters.html\"><i class=\"icon-group\"></i>Бойцы Команды</a>\n" +
"                   \n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"gallery.html\"><i class=\"icon-camera\"></i>Галерея</a>\n" +
"					\n" +
"			  </li>\n" +
"				<li>\n" +
"					<a  href=\"guns.html\"><i class=\"icon-screenshot\"></i>Вооружение</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"snaryaga.html\"><i class=\"icon-shield\"></i>Снаряжение</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"noobies.html\"><i class=\"icon-user\"></i>Вступить в Команду</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"docs.html\"><i class=\"icon-book\"></i>Документы</a>\n" +
"				</li>\n" +
"                \n" +
"		  </ul>\n" +
"		</nav>\n" +
"\n" +
"	</div><!--end mainWrap-- >\n" +
"	\n" +
"	<!-- pcvector.net -->\n" +
"\n" +
"\n" +
"		<h3 class=\"title\">Главная страница</h3>\n" +
"		<p class=\"back\"><strong>Цель создания СК \"БрОН\" - объединить единомышленников для организации интересного, активного, экстремального отдыха, приятного общения в хорошей компании как на играх, так и вне страйкбольных мероприятий. <br>\n" +
"Мы используем российское оружие и снаряжение, стоящее на вооружении ВС РФ, но не занимаемся точным моделированием. При этом соблюдаем единообразие в форме одежды, снаряжении и вооружении. <br>\n" +
"С 21 июня 2017 команда официально состоит в блоке \"СОЮЗ\" <a href=\"http://www.airsoftgunspb.ru/forum/index.php\" target=\"_blank\"><i>Санкт-Петербургской Ассоциации Страйкбола (СПАС)</i></a>. <br>\n" +
"СК \"БрОН\" образована 07 июля 2012 года в Санкт-Петербурге и на данный момент насчитывает !!!F бойц!!!SF, !!!C кандидат!!!SC и !!!R резервист!!!SR. </strong></p>\n" +
"\n" +
"	\n" +
"</body>\n" +
"</html>";

/**Шаблон ПК-версии страницы навигации по составу команды*/
public static final String NAVIGATION = "<!doctype html>\n" +
"<html>\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"<title>СК &quot;БрОН&quot;</title>\n" +
"<script language=\"javascript\">\n" +
"\n" +
"</script>\n" +
"\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\" >\n" +
"<!-- Телефон -->\n" +
"<link href=\"tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\">\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
"</head>\n" +
"\n" +
"\n" +
"\n" +
"<body>\n" +
"\n" +
"<map name=\"Map\" id=\"Map\">\n" +
"  <area shape=\"rect\" coords=\"529,269,583,274\" href=\"#\">\n" +
"  <area shape=\"rect\" coords=\"2,6,7,8\" href=\"#\">\n" +
"</map>\n" +
"\n" +
"<table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"  <tr>\n" +
"    <td colspan=\"3\"><img src=\"img/title-pc.png\" width=\"1193\" height=\"297\" id=\"pk\" usemap=\"#pkMap\"; border=\"0\">\n" +
"    <map name=\"pkMap\">\n" +
"   \n" +
"  <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"fighters.html\">\n" +
" \n" +
"    </map></td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td width=\"6%\" height=\"465\" valign=\"top\">&nbsp;\n" +
"    \n" +
"    \n" +
"    <img src=\"menu/menu2.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\">\n" +
"    </td>\n" +
"	\n" +
"    \n" +
"   \n" +
"    <td width=\"28%\" height=\"1565\" valign=\"top\"><img src=\"img/title-tel.png\" id=\"mobile\" align=\"center\" width=\"768\" height=\"150\"><br><img src=\"menu/zaglav/09.png\" width=\"825\" height=\"92\">\n" +
"<p>&nbsp;</p>\n" +
"   	!!!FIGHTERS\n" +
"    <p class=\"fighter\">\n" +
"	Кандидаты:\n" +
"	!!!CANDIDATES  \n" +
"        \n" +
"	<p class=\"fighter\">\n" +
"	Резервисты:\n" +
"	!!!RESERVES\n" +
"       \n" +
"      <p>      \n" +
"    </td>\n" +
"  </tr>\n" +
"  </table>\n" +
"\n" +
"\n" +
"\n" +
" </td>\n" +
"    <td width=\"26%\" align=\"left\" valign=\"top\">&nbsp;</td>\n" +
"  </tr>\n" +
"<tr>\n" +
"    <td colspan=\"3\" valign=\"top\">&nbsp;</td>\n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"</map>\n" +
"\n" +
"<map name=\"menuMap\">\n" +
"  <area shape=\"rect\" coords=\"172,8,263,36\" href=\"main.html\">\n" +
"  <area shape=\"rect\" coords=\"174,106,285,132\" href=\"ustav.html\">\n" +
"  <area shape=\"rect\" coords=\"175,159,310,182\" href=\"snaryaga.html\">\n" +
"  <area shape=\"rect\" coords=\"174,210,310,236\" href=\"guns.html\">\n" +
"  <area shape=\"rect\" coords=\"173,259,266,283\" href=\"gallery.html\">\n" +
"  <area shape=\"rect\" coords=\"172,307,395,334\" href=\"rules.html\">\n" +
"  <area shape=\"rect\" coords=\"171,352,391,375\" href=\"noobies.html\">\n" +
"</map>\n" +
"</body>\n" +
"</html>";

/**Шаблон мобильной версии страницы навигации по составу команды*/
public static final String NAVIGATION_MOB = "<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"<head>\n" +
"	<meta charset=\"utf-8\">\n" +
"	<title>СК \"БрОН\"</title>\n" +
"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"\n" +
"	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<link rel=\"stylesheet\" href=\"css/font-awesome.css\" >\n" +
"\n" +
"	\n" +
"\n" +
"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\" type=\"text/javascript\" ></script>\n" +
"    <script src=\"js/menu.js\" type=\"text/javascript\"></script> \n" +
"\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"		<h1 class=\"title\">СК \"БрОН\"</h1>\n" +
"<div class=\"topHeader\"></div>\n" +
"\n" +
"	<div class=\"mainWrap\">\n" +
"	<a id=\"touch-menu\" class=\"mobile-menu\" href=\"#\"><i class=\"icon-reorder\"></i>Меню</a>\n" +
"\n" +
"		<nav>\n" +
"			<ul class=\"menu\">\n" +
"				<li>\n" +
"					<a href=\"main.html\"><i class=\"icon-home\"></i>Главная</a>\n" +
"					\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"#\"><i class=\"icon-group\"></i>Бойцы Команды</a>\n" +
"                    \n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"gallery.html\"><i class=\"icon-camera\"></i>Галерея</a>\n" +
"					\n" +
"			  </li>\n" +
"				<li>\n" +
"					<a  href=\"guns.html\"><i class=\"icon-screenshot\"></i>Вооружение</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"snaryaga.html\"><i class=\"icon-shield\"></i>Снаряжение</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"noobies.html\"><i class=\"icon-user\"></i>Вступить в Команду</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"docs.html\"><i class=\"icon-book\"></i>Документы</a>\n" +
"				</li>\n" +
"                \n" +
"		  </ul>\n" +
"		</nav>\n" +
"\n" +
"	</div><!--end mainWrap-- >\n" +
"	\n" +
"	<!-- pcvector.net -->\n" +
"\n" +
"\n" +
"		<h3 class=\"title\">Бойцы команды</h3>\n" +
"		<p class=\"back\"> \n" +
"		<p>\n" +
"		!!!FIGHTERS\n" +
"<p>        \n" +
"<pre>&nbsp;</pre>\n" +
" <h3 class=\"back\">Кандидаты:</h3>\n" +
"  !!!CANDIDATES  \n" +
"      <p>        \n" +
"<pre>&nbsp;</pre>\n" +
" <h3 class=\"back\">Резервисты:</h3>\n" +
"  !!!RESERVES  \n" +
"      <p>\n" +
"	  </p></p>\n" +
"\n" +
"	\n" +
"</body>\n" +
"</html>";

/**Шаблон ПК-версии страницы о наборе в команду*/
public static final String NOOBIES = "<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"<!-- Телефон -->\n" +
"<link href=\"tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\">\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\" >\n" +
"</head>\n" +
"\n" +
"\n" +
"<title>Вступление в команду</title>\n" +
"\n" +
"\n" +
"<body>\n" +
"\n" +
"<table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"\n" +
"  <tr>\n" +
"     <td colspan=\"3\"><img src=\"img/title-pc.png\" width=\"1193\" height=\"297\" usemap=\"#pkMap\" id=\"pk\"; border=\"0\">\n" +
"    <map name=\"pkMap\">\n" +
"   <area shape=\"poly\" coords=\"182,50,186,65,185,93,187,105,188,124,186,142,186,161,187,185,186,196,194,210,205,221,219,232,238,242,251,248,265,255,279,262,288,267,300,261,312,254,331,250,348,240,364,231,378,218,391,201,393,186,391,164,392,146,391,131,393,115,392,102,392,83,394,72,395,61,399,50,382,45,355,31,325,19,311,17,281,16,256,19,239,28,224,33,207,42\" href=\"main.html\">\n" +
"  <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"fighters.html\">\n" +
"</map></td>\n" +
"  <tr>\n" +
"    <td width=\"26%\" rowspan=\"3\" valign=\"top\"><p id=\"p\">&nbsp;</p>\n" +
"      \n" +
"    <img src=\"menu/menu8.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\"></td>\n" +
"    \n" +
"     <map name=\"menuMap\">\n" +
"  <area shape=\"rect\" coords=\"177,58,351,80\" href=\"fighters.html\">\n" +
"  <area shape=\"rect\" coords=\"175,107,282,130\" href=\"ustav.html\">\n" +
"  <area shape=\"rect\" coords=\"175,214,310,236\" href=\"guns.html\">\n" +
"  <area shape=\"rect\" coords=\"174,158,307,182\" href=\"snaryaga.html\">\n" +
"  <area shape=\"rect\" coords=\"173,261,264,284\" href=\"gallery.html\">\n" +
"  <area shape=\"rect\" coords=\"174,309,390,331\" href=\"rules.html\">\n" +
"  <area shape=\"rect\" coords=\"173,4,262,28\" href=\"main.html\">\n" +
"    </map>\n" +
"    \n" +
"    \n" +
"    <td width=\"78%\" valign=\"top\"><p align=\"left\"><img src=\"menu/zaglav/08.png\" width=\"825\" height=\"92\"> </p>\n" +
"    <p>&nbsp;</p>\n" +
"    <p class=\"text\">\n" +
"	!!!TEXT\n" +
"    </p>\n" +
"	!!!LINK\n" +
"    </td>\n" +
"    <td width=\"24%\" rowspan=\"2\" valign=\"top\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td valign=\"top\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\">&nbsp;</td>\n" +
"  </tr>\n" +
"</table>";

/**Шаблон мобильной версии страницы о наборе в команду*/
public static final String NOOBIES_MOB = "<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"<head>\n" +
"	<meta charset=\"utf-8\">\n" +
"	<title>СК \"БрОН\"</title>\n" +
"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"\n" +
"	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<link rel=\"stylesheet\" href=\"css/font-awesome.css\" >\n" +
"\n" +
"	\n" +
"\n" +
"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\" type=\"text/javascript\" ></script>\n" +
"    <script src=\"js/menu.js\" type=\"text/javascript\"></script> \n" +
"\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"		<h1 class=\"title\">СК \"БрОН\"</h1>\n" +
"<div class=\"topHeader\"></div>\n" +
"\n" +
"	<div class=\"mainWrap\">\n" +
"	<a id=\"touch-menu\" class=\"mobile-menu\" href=\"#\"><i class=\"icon-reorder\"></i>Меню</a>\n" +
"\n" +
"		<nav>\n" +
"			<ul class=\"menu\">\n" +
"				<li>\n" +
"					<a href=\"main.html\"><i class=\"icon-home\"></i>Главная</a>\n" +
"					\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"fighters.html\"><i class=\"icon-group\"></i>Бойцы Команды</a>\n" +
"                   \n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"gallery.html\"><i class=\"icon-camera\"></i>Галерея</a>\n" +
"					\n" +
"			  </li>\n" +
"				<li>\n" +
"					<a  href=\"guns.html\"><i class=\"icon-screenshot\"></i>Вооружение</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"snaryaga.html\"><i class=\"icon-shield\"></i>Снаряжение</a>\n" +
"				</li>\n" +
"				<li>\n" +
"					<a  href=\"noobies.html\"><i class=\"icon-user\"></i>Вступить в Команду</a>\n" +
"				</li>\n" +
"                <li>\n" +
"					<a  href=\"docs.html\"><i class=\"icon-book\"></i>Документы</a>\n" +
"				</li>\n" +
"                \n" +
"		  </ul>\n" +
"		</nav>\n" +
"\n" +
"	</div><!--end mainWrap-- >\n" +
"	\n" +
"	<!-- pcvector.net -->\n" +
"\n" +
"\n" +
"		<h3 class=\"title\">Вступить в команду</h3>\n" +
"		<p class=\"back\"\n" +
"		!!!TEXT\n" +
"		</p>\n" +
"        <p class=\"back\">\n" +
"		!!!LINK\n" +
"		<br>\n" +
"          \n" +
"        </p>\n" +
"        <p>&nbsp;</p>\n" +
"		<h5 class=\"back\">&nbsp;</h5>\n" +
"		<p>&nbsp;</p>\n" +
"<p сlass=\"back\">&nbsp;</p>\n" +
"\n" +
"	\n" +
"</body>\n" +
"</html>";

/**Шаблон страницы сайта, содержащей правила страйкбола*/
public static final String RULES = "<head>\n" +
"<<script type=\"text/javascript\"\n" +
"src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n" +
"<script type=\"text/javascript\">\n" +
"$(document).ready(function(){\n" +
" $('.spoiler_links').click(function(){\n" +
"  $(this).parent().children('div.spoiler_body').toggle('normal');\n" +
"  return false;\n" +
" });\n" +
"});\n" +
"</script>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"<!-- Телефон -->\n" +
"<link href=\"tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\">\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\" >\n" +
"<style type=\"text/css\">\n" +
" .spoiler_body { display:none; font-weight:bold; font-size: 18px;}\n" +
" .spoiler_links { cursor:pointer; font-weight:bold; font-size: 24px;  }\n" +
" .bron { color:#5e5d01; }\n" +
"</style>\n" +
"</head>\n" +
"<style type=\"text/css\"></style>\n" +
"<title>Правила страйкбола</title>\n" +
"<body>\n" +
"\n" +
"<table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"\n" +
"  <tr>\n" +
"     <td colspan=\"3\"><img src=\"img/title-pc.png\" width=\"1193\" height=\"297\" usemap=\"#pkMap\" id=\"pk\"; border=\"0\">\n" +
"    <map name=\"pkMap\">\n" +
"   <area shape=\"poly\" coords=\"186,53,190,68,189,96,191,108,192,127,190,145,190,164,191,188,190,199,198,213,209,224,223,235,242,245,255,251,269,258,283,265,292,270,304,264,316,257,335,253,352,243,368,234,382,221,395,204,397,189,395,167,396,149,395,134,397,118,396,105,396,86,398,75,399,64,403,53,386,48,359,34,329,22,315,20,285,19,260,22,243,31,228,36,211,45\" href=\"main.html\">\n" +
"  <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"fighters.html\">\n" +
"</map></td>\n" +
"  <tr>\n" +
"    <td width=\"22%\" rowspan=\"2\" valign=\"top\"><p id=\"p\">&nbsp;</p>\n" +
"     \n" +
"      <img src=\"menu/menu7.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\"></td>\n" +
"      \n" +
"      \n" +
"      <map name=\"menuMap\">\n" +
"  <area shape=\"rect\" coords=\"164,60,343,83\" href=\"fighters.html\">\n" +
"  <area shape=\"rect\" coords=\"170,108,274,133\" href=\"ustav.html\">\n" +
"  <area shape=\"rect\" coords=\"154,211,299,235\" href=\"guns.html\">\n" +
"  <area shape=\"rect\" coords=\"164,158,296,182\" href=\"snaryaga.html\">\n" +
"  <area shape=\"rect\" coords=\"168,262,256,284\" href=\"gallery.html\">\n" +
"  <area shape=\"rect\" coords=\"155,352,382,375\" href=\"noobies.html\">\n" +
"  <area shape=\"rect\" coords=\"160,8,256,28\" href=\"main.html\">\n" +
"    </map>\n" +
"      \n" +
"      \n" +
"    <td width=\"78%\" valign=\"top\"><pre><font size=\"+1\"><img src=\"menu/zaglav/07.png\" width=\"825\" height=\"92\">\n" +
"\n" +
"\n" +
"<span class=\"text\">Каждый боец &quot;СК БрОН&quot; принимает и обязуется исполнять следующие правила:</span><span style=\"text-align: left; color: #5E5D01; font-family: Impact;\">\n" +
"\n" +
"</span></pre>\n" +
"</font>\n" +
"!!!CHAPTERS\n" +
"     <span class=\"text\"> <br><br>\n" +
"	 !!!SOURCE\n" +
"	 </span></font></td>\n" +
"    <td width=\"24%\" valign=\"top\">&nbsp;</td>\n" +
"  \n" +
"  <td width=\"2%\"></td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\">&nbsp;</td>\n" +
"  </tr>\n" +
"</table>";

/**Шаблон страницы сайта, содержащей Устав команды*/
public static final String REGULATION ="<head>\n" +
"\n" +
"<<script type=\"text/javascript\"\n" +
"src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n" +
"<script type=\"text/javascript\">\n" +
"$(document).ready(function(){\n" +
" $('.spoiler_links').click(function(){\n" +
"  $(this).parent().children('div.spoiler_body').toggle('normal');\n" +
"  return false;\n" +
" });\n" +
"});\n" +
"</script>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<meta name=\"viewport\" content=\"width=device-width\">\n" +
"<!-- Телефон -->\n" +
"<link href=\"tel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (max-width:320px)\">\n" +
"<!-- Планшет -->\n" +
"<link href=\"pla.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:321px) and (max-width:768px)\">\n" +
"<!-- Рабочий стол -->\n" +
"<link href=\"pc.css\" rel=\"stylesheet\" type=\"text/css\" media=\"only screen and (min-width:769px)\">\n" +
"<link href=\"text.css\" rel=\"stylesheet\" type=\"text/css\" >\n" +
"\n" +
"<style type=\"text/css\">\n" +
" .spoiler_body { display:none; font-weight:bold; font-size: 18px;}\n" +
" .spoiler_links { cursor:pointer; font-weight:bold; font-size: 24px;  }\n" +
" .bron { color:#5e5d01; }\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"\n" +
"<style type=\"text/css\">\n" +
"   \n" +
"</style>\n" +
"<title>Устав СК &quot;БрОН&quot;</title>\n" +
"<body><table width=\"100%\" border=\"0\" align=\"center\" cellspacing=\"30\">\n" +
"\n" +
"  <tr>\n" +
"     <td colspan=\"2\"><img src=\"img/title-pc.png\" width=\"1193\" height=\"297\" usemap=\"#pkMap\" id=\"pk\"; border=\"0\">\n" +
"    <map name=\"pkMap\">\n" +
"   <area shape=\"poly\" coords=\"186,53,190,68,189,96,191,108,192,127,190,145,190,164,191,188,190,199,198,213,209,224,223,235,242,245,255,251,269,258,283,265,292,270,304,264,316,257,335,253,352,243,368,234,382,221,395,204,397,189,395,167,396,149,395,134,397,118,396,105,396,86,398,75,399,64,403,53,386,48,359,34,329,22,315,20,285,19,260,22,243,31,228,36,211,45\" href=\"main.html\">\n" +
"  <area shape=\"poly\" coords=\"728,61,1131,64,1138,74,1144,89,1153,100,1144,116,1148,130,1147,149,1145,159,1145,171,1147,181,1131,193,1132,204,1136,218,1138,229,725,229,720,218,719,206,714,198,711,187,707,169,712,132,693,112\" href=\"fighters.html\">\n" +
"</map></td>\n" +
"  <tr align=\"right\">\n" +
"    <td width=\"22%\" rowspan=\"2\" valign=\"top\"><p id=\"p\">&nbsp;</p>\n" +
"    <img src=\"menu/menu3.png\" name='menu' width=\"430\" height=\"376\" usemap=\"#menuMap\">\n" +
"    \n" +
"    <map name=\"menuMap\">\n" +
"  <area shape=\"rect\" coords=\"164,57,347,82\" href=\"fighters.html\">\n" +
"  <area shape=\"rect\" coords=\"167,159,298,182\" href=\"snaryaga.html\">\n" +
"  <area shape=\"rect\" coords=\"171,214,302,236\" href=\"guns.html\">\n" +
"  <area shape=\"rect\" coords=\"167,259,256,285\" href=\"gallery.html\">\n" +
"  <area shape=\"rect\" coords=\"164,308,390,335\" href=\"rules.html\">\n" +
"  <area shape=\"rect\" coords=\"163,350,381,375\" href=\"noobies.html\">\n" +
"  <area shape=\"rect\" coords=\"165,7,265,31\" href=\"main.html\">\n" +
"    </map>\n" +
"    \n" +
"    </td>\n" +
"    <td width=\"78%\" align=\"left\"><p><img src=\"menu/zaglav/02.png\" width=\"825\" height=\"92\"></p>\n" +
"<br>\n" +
"<span class=\"text\">Каждый боец &quot;СК БрОН&quot; принимает и обязуется исполнять Устав Команды:</span><span style=\"text-align: left; text-size: 18; color: #5E5D01; font-family: Impact;\">\n" +
"\n" +
"</span><br><br>\n" +
"	  <div>\n" +
" !!!CHAPTERS\n" +
"<br>  <br>  <br>  \n" +
"<a href=\"\" class=\"spoiler_links\">ПРИЛОЖЕНИЕ</a>\n" +
" <div class=\"spoiler_body bron\">\n" +
" <img src=\"img/snar/shevron.png\" width=\"433\" height=\"265\">\n" +
" </div></div><div>\n" +
" \n" +
"    </td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>&nbsp;</td>\n" +
"  </tr>\n" +
"</table>";
    
    
}
