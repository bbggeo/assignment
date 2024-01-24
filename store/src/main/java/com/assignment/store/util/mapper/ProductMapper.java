package com.assignment.store.util.mapper;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.Product;
import com.assignment.store.dto.product.ClothingApparelDTO;
import com.assignment.store.dto.product.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class ProductMapper {

    public static Product mapToProduct(ProductDTO productDTO) {
        Product product = null;
        ModelMapper mapper = new ModelMapper();
        switch (productDTO) {
            case ClothingApparelDTO clothingApparelDTO:
                return mapper.map(clothingApparelDTO, ClothingApparel.class);
            default:
                return mapper.map(productDTO, Accessory.class);
        }
    }

    public static ProductDTO mapToProductDto(Product product) {
        ModelMapper mapper = new ModelMapper();
        switch (product) {
            case ClothingApparel clothingApparel:
                return mapClothingApparelToProductDTO(mapper, (ClothingApparel) product);
            case Accessory accessory:
                return mapAccessoryToProductDTO(mapper, (Accessory) product);
            default:
                throw new RuntimeException();
        }
    }

    public static ProductDTO mapClothingApparelToProductDTO(ModelMapper mapper, ClothingApparel clothingApparel) {
        if(mapper.getTypeMap(ClothingApparel.class, ProductDTO.class) == null) {
            TypeMap<ClothingApparel, ProductDTO> clothingMap = mapper.createTypeMap(ClothingApparel.class, ProductDTO.class);
            clothingMap.addMappings(mapping -> {
                mapping.map(productSrc -> productSrc.getSupplier().getName(), ProductDTO::setSupplier);
                mapping.map(productSrc -> productSrc.getMaterial().getName(), ProductDTO::setMaterial);
                mapping.map(productSrc -> productSrc.getMaterial().getDescription(), ProductDTO::setMaterialDescription);

            });
        }
        return mapper.map(clothingApparel, ProductDTO.class);
    }

    private static ProductDTO mapAccessoryToProductDTO(ModelMapper mapper, Accessory accessory) {
        if (mapper.getTypeMap(Accessory.class, ProductDTO.class) == null) {
            TypeMap<Accessory, ProductDTO> clothingMap = mapper.createTypeMap(Accessory.class, ProductDTO.class);
            clothingMap.addMappings(mapping -> {
                mapping.map(productSrc -> productSrc.getSupplier().getName(), ProductDTO::setSupplier);
            });
        }
        return mapper.map(accessory, ProductDTO.class);
    }
}
