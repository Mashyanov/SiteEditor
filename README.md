# SiteEditor
Программа для редактирования сайта [страйкбольной](https://ru.wikipedia.org/wiki/Страйкбол) команды 

Сайт хранится на дешевеньком FTP-хостинге. Задача - сделать десктоп-приложение для редактирования сайта.
Все изменения вносятся как в ПК, так и в мобильную версии сайта. 
При старте программы она автоматически проверяет наличие обновлений и, если находит, скачивает его с FTP-сервера.

#### **Раздел бойцы команды**
Позволяет редактировать состав команды: добавлять и удалять членов, менять их данные, назначать и снимать с должностей. 
Менять фотографии. При внесении изменений обновляются страницы члена команды, страница состава команды и главная страница, 
на которой указывается число полноправных членов команды, кандидиатов и резевистов. Так же на базе основной фоторграфии профиля 
автоматически отрисовываются все изображения для сайта(миниатюры, фотогрфаия с подписанным внизу позывным для меню навигации и т.д.)

#### **Раздел галерея**
Позволяет добавлять, удалять и менять местами фотографии в фотогалерее сайта. При добавлении новой фотографии миниатюра создается 
автоматически.
Позволяет удалять с сайта видео, а так же добавлять их, путем встраивания видео с YouTube по имеющейся в программе подсказке-инструкции

#### **Разделы 'Устав команды' и 'Правила Страйкбола'**
Разделы позволяют вносить изменения в устав команды и в правила страйкбола: добавлять, удалять и менять местами главы. Менять их содержимое.

#### **Раздел Набор в команду**
Позволяет редактировать страницу сайта, посвященную набору новичков. На выбор предлагается две версии страницы: ***набор открыт*** и
***набор закрыт***. В каждом разделе есть стандартный тескст, однако он может быть заменен на пользовательский. При желании можно вернуть 
вариант текста по умолчанию. При выборе варианта ***набор открыт*** в страницу встраивается кнопка, открывающая страницу в ВК человека, 
отвественного за прием новичков. Адрес этой страницы так же завадется в окне программы **Набор в команду**

В этом разделе реализован метод, заменяющий определенные слова(вооружение, боец, снаряжение, правила, устав и т.д.) и все их 
склонения в едниственном и множенственном числах гиперссыками на соответствующие страницы сайта. 

#### **Опции**
Несет в себе сугубо функции настройки. Там можно как включить или выключить отладочную печать в консоль, печать о проверках соединения с 
FTP-сервером, так и указывать параметры, необходимые для подключения к FTP-серверу: логин, пароль и домен. 

В планах переделать всё это безобразие с помощью MySQL БД.
