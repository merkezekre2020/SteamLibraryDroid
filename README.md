# 🎮 SteamLibraryDroid

Steam kütüphaneni Android tarafında daha düzenli yönetmek için hazırlanan bir mobil uygulama iskeleti.

Şu an proje **MVP / prototip scaffold** aşamasında ama artık daha production-odaklı akışlar içeriyor: giriş doğrulaması, Steam profil bağlama doğrulaması, kütüphane arama/filtreleme ve ayarlar ekranı biraz daha gerçekçi hale getirildi. 🚧

## ✨ Özellik hedefi

- Steam profilini bağlama
- Kütüphane içe aktarma
- Oyunları listeleme, arama ve filtreleme
- Oyunlara durum ekleme
  - backlog
  - playing
  - completed
  - dropped
- Favori, puan ve not yönetimi
- Local cache + backend senkron yaklaşımı

## 🧰 Tech stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM-benzeri yapı**
- **Retrofit**
- **Room**
- **PocketBase**
- **Material 3**

## 📁 Proje yapısı

- `app/` → Android uygulaması
- `backend/` → PocketBase şema dosyaları
- `docs/` → ürün, mimari ve build notları

## 🧱 Şu anki durum

### ✅ Hazır olanlar
- Compose navigation iskeleti
- auth / profile link / library / detail / settings ekran akışı
- giriş ekranında temel doğrulama
- Steam profil bağlama ekranında input doğrulama
- library ekranında arama + filtreleme
- Steam API ve PocketBase için network iskeleti
- Room entity / dao / database taslağı
- dependency wiring başlangıcı
- GitHub Actions ile **manuel tetiklenen debug APK workflow**

### ⚠️ Eksik / geliştirme aşamasında olanlar
- gerçek PocketBase auth entegrasyonu
- gerçek Steam import akışı
- Room + remote data + UI tam entegrasyonu
- AndroidX ViewModel + coroutine state yönetiminin tam taşınması
- build kırıklarının gerçek Gradle ortamında tamamen temizlenmesi

## ⚙️ GitHub Actions

Repo içinde debug APK üretmek için bir workflow vardır:

- Dosya: `.github/workflows/debug-apk.yml`
- Tetikleme: **sadece manual (`workflow_dispatch`)**

GitHub arayüzünden çalıştırınca debug APK build etmeye çalışır ve artifact olarak yükler. 📦

## 🛠️ Lokal geliştirme notları

Bu repo şu an Android Studio veya Java + Gradle bulunan bir ortamda açılıp doğrulanmalıdır.

Önerilen adımlar:

1. Android Studio ile projeyi aç
2. Gradle wrapper üret (`gradle wrapper`) gerekiyorsa üret
3. Debug build dene:
   - `./gradlew :app:assembleDebug`
4. Lint dene:
   - `./gradlew :app:lint`

## 🔐 Konfigürasyon

Örnek environment bilgileri:

- `.env.example`

Önemli notlar:
- Steam API key repoya hardcode edilmemeli
- PocketBase URL geliştirme ortamına göre ayarlanmalı

## 📚 Dokümantasyon

İlgili dosyalar:
- `docs/product-plan.md`
- `docs/architecture.md`
- `docs/backend-setup.md`
- `docs/build-status.md`
- `docs/local-cache-plan.md`

## 📜 Lisans

MIT
