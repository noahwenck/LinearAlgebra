package com.wenck.linearalgebra.rowreduction;

import org.springframework.stereotype.Controller;

/**
 * Constructor class responsible for HTTP requests related to Row Reduction
 */

@Controller
public class RowReductionController {

    private RowReductionService rowReductionService;
    public RowReductionController(RowReductionService rowReductionService) {
        this.rowReductionService = rowReductionService;
    }

}
