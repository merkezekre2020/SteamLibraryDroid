# Architecture

## Android

- UI: Jetpack Compose
- State: MVVM
- DI: Manual başlangıç, sonra Hilt eklenebilir
- Local cache: Room
- Preferences: DataStore
- Network: Retrofit + Kotlinx Serialization
- Images: Coil

## Backend

- PocketBase
- Auth collection: users
- Collections:
  - steam_profiles
  - games
  - user_game_entries
  - sync_logs

## Data flow

1. Kullanıcı giriş yapar
2. Steam profili bağlanır
3. Sync işlemi games ve user_game_entries verisini günceller
4. Android app PocketBase + local cache üzerinden listeyi sunar
5. Uygulama açılışında cache varsa önce Room'dan liste gösterilebilir

## Sync stratejisi

- İlk sürümde manuel sync butonu
- Son sync zamanı saklanır
- Hatalar sync_logs içine yazılır
