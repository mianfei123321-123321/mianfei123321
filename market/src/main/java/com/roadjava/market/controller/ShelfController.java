package com.roadjava.market.controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.roadjava.market.bean.req.shelf.ShelfPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.shelf.ShelfAddReq;
import com.roadjava.market.bean.req.shelf.ShelfUpdateReq;
import com.roadjava.market.bean.vo.ShelfVO;
import com.roadjava.market.service.ShelfService;
/**
  * 货架Controller层
  * @author 乐之者java
  * @see <a href="https://www.roadjava.com">乐之者java</a>
  */
@RestController
@RequestMapping("/shelf")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ShelfController {

    private final ShelfService shelfService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated ShelfAddReq addReq) {
        shelfService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<ShelfVO>> queryPage(@RequestBody ShelfPageReq pageReq) {
        return  shelfService.queryPage(pageReq);
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("id") Long id) {
        shelfService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

      /**
      * 通过主键查询一个实体
      */
      @GetMapping("/queryById")
      public Result<ShelfVO> queryById(@RequestParam("id") Long id) {
          return  Result.buildSuccess(shelfService.queryById(id));
      }

      @PostMapping("/modifyById")
      public Result<String> modifyById(@RequestBody @Validated ShelfUpdateReq updateReq) {
          shelfService.modifyById(updateReq);
          return Result.buildSuccess("更新成功");
      }

      @GetMapping("/queryAll")
      public Result<List<ShelfVO>> queryAll() {
          return  Result.buildSuccess(shelfService.queryAll());
      }
}