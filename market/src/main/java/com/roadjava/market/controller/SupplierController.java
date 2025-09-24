package com.roadjava.market.controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.roadjava.market.bean.req.supplier.SupplierPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.supplier.SupplierAddReq;
import com.roadjava.market.bean.req.supplier.SupplierUpdateReq;
import com.roadjava.market.bean.vo.SupplierVO;
import com.roadjava.market.service.SupplierService;
/**
  * 供应商表Controller层
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated SupplierAddReq addReq) {
        supplierService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<SupplierVO>> queryPage(@RequestBody SupplierPageReq pageReq) {
        return  supplierService.queryPage(pageReq);
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("id") Long id) {
        supplierService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

      /**
      * 通过主键查询一个实体
      */
      @GetMapping("/queryById")
      public Result<SupplierVO> queryById(@RequestParam("id") Long id) {
          return  Result.buildSuccess(supplierService.queryById(id));
      }

      @PostMapping("/modifyById")
      public Result<String> modifyById(@RequestBody @Validated SupplierUpdateReq updateReq) {
          supplierService.modifyById(updateReq);
          return Result.buildSuccess("更新成功");
      }

      @GetMapping("/queryAll")
      public Result<List<SupplierVO>> queryAll() {
          return  Result.buildSuccess(supplierService.queryAll());
      }
}