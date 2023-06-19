package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
 private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }


    @Override
    public Optional<Block> findBlockByColor(String color) {
      for (Block b: blocks){
          if (b.getColor().contains(color)){
              return Optional.of(b);
          }else if(b instanceof CompositeBlock){
              Optional<Block> optionalBlock = findBlockByColorComposite((CompositeBlock) b,color)
              if (optionalBlock.isPresent()){
                  return optionalBlock;
              }

          }
      }
      return Optional.empty();
    }

    public Optional<Block> findBlockByColorComposite(CompositeBlock compositeBlock,String color ){
        List<Block> blockList = compositeBlock.getBlocks();
        for (Block b:blockList){
            if (b.getColor().contains(color)){
                return Optional.of(b);
            } else if (b instanceof CompositeBlock) {
                Optional<Block>  optionalBlock = findBlockByColorComposite((CompositeBlock)b,color);
                if (optionalBlock.isPresent()){
                    return optionalBlock;
                }
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blockList = new ArrayList<>();
        for (Block b:blocks) {
            if (b.getMaterial().contains(material)){
                blockList.add(b);
            }
            if (b instanceof CompositeBlock){
                List<Block> block = ((CompositeBlock) b).getBlocks();
                blockList.addAll(findBlocksByMaterialComposite(blockList,material))
            }

        }
        return blockList ;
    }
    public List<Block> findBlocksByMaterialComposite(List<Block> list, String material){
        List<Block> blockList = new ArrayList<>();
        for (Block b:blocks){
            if (b.getMaterial().contains(material)){
                blockList.add(b)
            }
            if (b instanceof CompositeBlock){
                List<Block> list = ((CompositeBlock )b).getBlocks();
                blockList.addAll(findBlocksByMaterialComposite(blockList,String material));
            }
        }
        return blockList;
    }


    @Override
    public int count() {
        int count = 0;
        for (Block b:blocks) {
            count++;
        }
        return count;
    }
}
