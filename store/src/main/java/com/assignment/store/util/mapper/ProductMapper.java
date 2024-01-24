package com.assignment.store.util.mapper;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.Product;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.util.enums.ProductType;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductMapper {

    static Logger logger = LoggerFactory.getLogger(ProductMapper.class);

    public static Product mapToProduct(ProductDTO productDTO) {
        ModelMapper mapper = new ModelMapper();
        ProductType productType = ProductType.findByName(productDTO.getType()).get();
        if (productType.getCorrespondingClass().equals(ClothingApparel.class)) {
            return mapper.map(productDTO, ClothingApparel.class);
        } else {
                return mapper.map(productDTO, Accessory.class);
        }
    }

    public static ProductDTO mapToProductDto(Product product) {
        ModelMapper mapper = new ModelMapper();
        switch (product) {
            case ClothingApparel clothingApparel:
                return mapProductDTO(mapper, (ClothingApparel) product);
            case Accessory accessory:
                return mapAccessoryToProductDTO(mapper, (Accessory) product);
            default:
                throw new RuntimeException();
        }
    }

    public static ProductDTO mapProductDTO(ModelMapper mapper, ClothingApparel clothingApparel) {
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
            TypeMap<Accessory, ProductDTO> accessoryMap = mapper.createTypeMap(Accessory.class, ProductDTO.class);
            accessoryMap.addMappings(mapping -> {
                mapping.map(productSrc -> productSrc.getSupplier().getName(), ProductDTO::setSupplier);
                mapping.map(productSrc -> productSrc.getMaterial().getName(), ProductDTO::setMaterial);
                mapping.map(productSrc -> productSrc.getMaterial().getDescription(), ProductDTO::setMaterialDescription);
            });
        }
        logger.info("Mapped accessory");
        return mapper.map(accessory, ProductDTO.class);
    }
}
