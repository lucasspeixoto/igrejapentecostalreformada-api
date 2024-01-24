package com.platform.igrejapentecostalreformadaapi.controllers.indicators;

import com.platform.igrejapentecostalreformadaapi.data.vo.indicators.Statistics;
import com.platform.igrejapentecostalreformadaapi.services.indicators.StatisticsService;
import com.platform.igrejapentecostalreformadaapi.utils.constants.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/statistics")
@Tag(name = "Statistics", description = "Endpoints for Statistics data")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds all statistics data",
            description = "Service for find all the statistics data",
            tags = {"Statistics"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Statistics.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Statistics> findStatisticsData() throws Exception {

        return ResponseEntity.ok(this.statisticsService.findStatisticsData());
    }

}
