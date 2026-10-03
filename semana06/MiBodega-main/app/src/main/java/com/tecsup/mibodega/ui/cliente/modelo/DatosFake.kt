package com.tecsup.mibodega.ui.cliente.modelo

val listaCategorias = listOf("Todos", "Bebidas", "Abarrotes", "Dulces", "Snacks")

val listaProductosFake = listOf(
    Producto(
        id = 1,
        nombre = "Arroz Costeño",
        descripcion = "Arroz extra, grano largo, ideal para tus comidas.",
        precio = 4.50,
        categoria = "Abarrotes",
        imagenUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=300"
    ),
    Producto(
        id = 2,
        nombre = "Aceite Primor",
        descripcion = "Aceite vegetal 1 L, alto en vitaminas.",
        precio = 8.90,
        categoria = "Abarrotes",
        imagenUrl = "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?w=300"
    ),
    Producto(
        id = 3,
        nombre = "Leche Gloria",
        descripcion = "Leche evaporada entera 1 L.",
        precio = 5.20,
        categoria = "Abarrotes",
        imagenUrl = "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=300"
    ),
    Producto(
        id = 4,
        nombre = "Galleta Oreo",
        descripcion = "Galletas de chocolate rellenas 126g.",
        precio = 3.50,
        categoria = "Snacks",
        imagenUrl = "https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=300"
    ),
    Producto(
        id = 5,
        nombre = "Coca-Cola 1.5L",
        descripcion = "Bebida gaseosa sabor original 1.5 L.",
        precio = 6.50,
        categoria = "Bebidas",
        imagenUrl = "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?w=300"
    )
)