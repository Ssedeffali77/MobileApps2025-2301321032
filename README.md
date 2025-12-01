# SimpleRecipesApp
## Факултетен номер: 2301321032
## Идея
**SimpleRecipesApp** е мобилно приложение за управление на рецепти. Потребителите могат да добавят, редактират и изтриват рецепти, да виждат списък с всички рецепти и да навигират лесно между тях.  

## Как работи
Приложението използва **екран за списък на рецепти** и **екран за добавяне/редакция на рецепти**. Потребителят натиска Floating Action Button за добавяне или иконата за редакция на съществуваща рецепта. Всички промени се отразяват в списъка веднага.  

## Архитектура
Приложението е изградена по **MVVM архитектура**:
- **Model**: `Recipe` data class и Room база данни.  
- **View**: `ActivityMainBinding`, `AddRecipeActivity`, RecyclerView за списък с рецепти.  
- **ViewModel**: управлява логиката за добавяне, редакция и изтриване на рецепти.  

## Потребителски поток
1. Стартирате приложението → виждате списък с рецепти.  
2. Натискате `+` (FAB) за нова рецепта.  
3. Въвеждате заглавие и описание → Save.  
4. Натискате иконата Edit на съществуваща рецепта за редакция.  
5. Натискате Delete за изтриване на рецепта.  

## Стъпки за стартиране

git clone <your-repo-url>
Open project in Android Studio
Build & Run on emulator or device

## Тестови акаунти
Приложението не изисква регистрация. Всички функции са достъпни веднага.
## Скрийншотове


<img width="353" height="804" alt="Screenshot 2025-12-01 132040" src="https://github.com/user-attachments/assets/ed49dc54-5642-4cfe-8f53-99ef78ee4e26" />

<img width="358" height="805" alt="Screenshot 2025-12-01 132056" src="https://github.com/user-attachments/assets/a504e6dc-de3c-45e6-832f-335c9152a93a" />

<img width="353" height="802" alt="Screenshot 2025-12-01 132133" src="https://github.com/user-attachments/assets/5f2d66f3-1e7b-4b31-9328-bc146a68cb38" />


## APK
Файлът на APK се намира в:
[Свали APK](./apk/app-release.apk)

