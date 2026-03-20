# Backend Setup

## PocketBase

1. PocketBase indir ve çalıştır
2. `http://localhost:8090/_/` üzerinden admin oluştur
3. `backend/pocketbase-schema.json` içeriğine göre koleksiyonları oluştur
4. `users` auth collection aktif olsun
5. Android emulator için base URL varsayılanı `http://10.0.2.2:8090`

## Steam API

Steam kütüphanesini resmi API ile çekmek için API key gerekir.

Gerekli endpoint:
- `IPlayerService/GetOwnedGames/v0001/`

Notlar:
- Profil/kütüphane gizliyse veri gelmeyebilir
- İlk sürümde kullanıcıdan Steam ID alınır
- API key hiçbir zaman repoya hardcode edilmez

## Sonraki iş

- env/config yönetimi ekle
- PocketBase login ve kayıt akışını tamamla
- imported games verisini PocketBase'e yaz
- Room cache ile offline listele
