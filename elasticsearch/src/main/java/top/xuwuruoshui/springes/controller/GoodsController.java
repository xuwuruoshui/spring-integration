package top.xuwuruoshui.springes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xuwuruoshui.springes.pojo.Goods;
import top.xuwuruoshui.springes.service.GoodsService;

import java.io.IOException;
import java.util.List;

@RestController
public class GoodsController {
    private GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PutMapping("/parse/{keywords}")
    public Boolean insertGoods(@PathVariable("keywords")String keywords) throws IOException {
        return goodsService.parseGoods(keywords);
    }

    @GetMapping("/search/{keyword}/{page}/{size}")
    public List<Goods> getGoods(@PathVariable("keyword")String keyword,@PathVariable("page") int page,@PathVariable("size")int size) throws IOException {
        List<Goods> goods = goodsService.searchGoods(keyword, page, size);
        return goods;
    }
}
