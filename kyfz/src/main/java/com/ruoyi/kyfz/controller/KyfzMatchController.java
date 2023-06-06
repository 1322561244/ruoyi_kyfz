package com.ruoyi.kyfz.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kyfz.domain.KyfzMatch;
import com.ruoyi.kyfz.service.IKyfzMatchService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 匹配列表Controller
 * 
 * @author ruoyi
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/kyfz/match")
public class KyfzMatchController extends BaseController
{
    @Autowired
    private IKyfzMatchService kyfzMatchService;

    /**
     * 查询匹配列表列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzMatch kyfzMatch)
    {
        startPage();
        List<KyfzMatch> list = kyfzMatchService.selectKyfzMatchList(kyfzMatch);
        return getDataTable(list);
    }

    /**
     * 导出匹配列表列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:export')")
    @Log(title = "匹配列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzMatch kyfzMatch)
    {
        List<KyfzMatch> list = kyfzMatchService.selectKyfzMatchList(kyfzMatch);
        ExcelUtil<KyfzMatch> util = new ExcelUtil<KyfzMatch>(KyfzMatch.class);
        util.exportExcel(response, list, "匹配列表数据");
    }

    /**
     * 获取匹配列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:query')")
    @GetMapping(value = "/{matchId}")
    public AjaxResult getInfo(@PathVariable("matchId") Long matchId)
    {
        return success(kyfzMatchService.selectKyfzMatchByMatchId(matchId));
    }

    /**
     * 新增匹配列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:add')")
    @Log(title = "匹配列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzMatch kyfzMatch)
    {
        return toAjax(kyfzMatchService.insertKyfzMatch(kyfzMatch));
    }

    /**
     * 修改匹配列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:edit')")
    @Log(title = "匹配列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzMatch kyfzMatch)
    {
        return toAjax(kyfzMatchService.updateKyfzMatch(kyfzMatch));
    }

    /**
     * 删除匹配列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:remove')")
    @Log(title = "匹配列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{matchIds}")
    public AjaxResult remove(@PathVariable Long[] matchIds)
    {
        return toAjax(kyfzMatchService.deleteKyfzMatchByMatchIds(matchIds));
    }
}
