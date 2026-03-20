# Local Cache Plan

## Amaç

Steam kütüphanesi ve kullanıcıya özel durum/not verilerini cihazda saklamak.

## Room tabloları

- `games`
- `user_game_entries`

## İlk kullanım

- Sync sonrası oyunları `games` tablosuna yaz
- Kullanıcı not/durum değiştirince `user_game_entries` tablosuna yaz
- Liste ekranı önce cache'den açılabilir

## Sonraki adımlar

- Room database provider ekle
- coroutine dispatcher yönetimi ekle
- flow tabanlı dao dönüşü düşünebilirsin
- sync merge stratejisi yaz
