package com.chococompany.boundary;

import com.chococompany.control.dto.ChocolateTypeDtoV1;
import com.chococompany.control.dto.ChocolatePercentageDto;
import com.chococompany.control.exception.ApiVersionException;
import com.chococompany.control.mapper.ChocolateMapper;
import com.chococompany.entity.Chocolate;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/chocolate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChocolateResource {

    @Inject
    ChocolateMapper chocolateMapper;

    @POST
    @APIResponse(
            responseCode = "200",
            description = "Get cocoa percentage by chocolate type",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.STRING, implementation = ChocolatePercentageDto.class)
            )
    )
    @Path("/percentage")
    @Counted(name = "Quantidade de chamadas")
    @Timed(name = "Tempo de resposta")
    public Response percentageByType(@HeaderParam("X-API-VERSION") String apiVersion,
                                     @Valid @Param ChocolateTypeDtoV1 dto) throws ApiVersionException {

        if (!"1".equals(apiVersion) ) {
            throw new ApiVersionException("API Version does not correspond to V1");
        }

        var chocolate = Chocolate.findByType(dto.type().toUpperCase());
        var percentageDto = chocolateMapper.toChocolatePercentageDto(chocolate);

        Log.info("Returning percentage v1: " + percentageDto.toString());
        return Response.ok()
                .entity(percentageDto)
                .build();
    }
}