package ru.practicum.features.request;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.features.request.model.ItemRequestDto;
import ru.practicum.features.request.model.ItemRequestWithItems;
import ru.practicum.features.request.service.ItemRequestService;
import ru.practicum.exceptions.*;

import java.util.List;


@RestController
@RequestMapping(path = "/requests")
@AllArgsConstructor
public class ItemRequestController {

    private final ItemRequestService itemRequestService;

    static final String USER_ID = "X-Sharer-User-Id";

    @PostMapping
    public ItemRequestDto postItemRequest(@RequestHeader(USER_ID) Long userId,
                                          @RequestBody ItemRequestDto itemRequestDto) throws NotFoundException, ValidationException {
        return itemRequestService.postItemRequest(userId, itemRequestDto);
    }

    @GetMapping
    public List<ItemRequestWithItems> getItemRequestWithItemsByCreator(@RequestHeader(USER_ID) Long userId) throws NotFoundException {
        return itemRequestService.getItemRequestWithItemsByCreator(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestWithItems> getItemRequestDtoByUserId(@RequestHeader(USER_ID) Long userId,
                                                                @RequestParam(required = false, defaultValue = "0") Integer from,
                                                                @RequestParam(required = false, defaultValue = "10") Integer size) throws NotFoundException, ValidationException {
        return itemRequestService.getItemRequestWithItemsUserId(userId, from, size);
    }

    @GetMapping("/{requestId}")
    public ItemRequestWithItems getItemRequestDto(@RequestHeader(USER_ID) Long userId,
                                                  @PathVariable("requestId") Long requestId) throws NotFoundException {
        return itemRequestService.getItemRequestWithItemsById(userId, requestId);
    }
}
