# Steam Library Manager

Android için Steam kütüphane yöneticisi MVP scaffold.

## Stack

- Kotlin
- Jetpack Compose
- MVVM
- PocketBase
- Room / DataStore
- Retrofit
- Material 3

## MVP

- Kullanıcı girişi
- Steam ID / profil URL ile profil bağlama
- Kütüphane senkronu
- Oyun listeleme
- Oyun detay ekranı
- Durum / puan / not / favori yönetimi

## Modüller

- `docs/` ürün ve teknik plan
- `app/` Android uygulama iskeleti
- `backend/` PocketBase şema ve notlar

## Sonraki adımlar

1. PocketBase örneğini ayağa kaldır
2. Koleksiyonları import et
3. `.env.example` değerlerini gerçek config'e taşı
4. Auth + sync akışını gerçek backend'e bağla
5. Compose ekranlarını fake state yerine gerçek state ile besle

## Mevcut durum

- Compose navigation hazır
- Fake auth ve fake library repository hazır
- Retrofit tabanlı PocketBase ve Steam servis iskeleti eklendi
- Backend setup notları `docs/backend-setup.md` içinde
