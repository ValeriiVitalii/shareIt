package ru.practicum.shareit.features.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.features.booking.model.Booking;
import ru.practicum.shareit.features.user.model.User;

import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Booking b SET b.status = ?2 WHERE b.id = ?1")
    void patchBooking(Long bookingId, BookingStatus bookingStatus);

    List<Booking> findAllByBookerOrderByStartDesc(User userId);

    List<Booking> findAllByBookerAndStatusOrderByStartDesc(User userId, BookingStatus status);

    List<Booking> findAllByItemIdOwnerOrderByStartDesc(User userId);

    List<Booking> findAllByItemIdOwnerAndStatusOrderByStartDesc(User userId, BookingStatus status);

    @Query("SELECT b FROM Booking b where b.itemId.id = ?1 ORDER BY b.start")
    List<Booking> findAllByItemIdOrderByStartAsc(Long itemId);

    @Query("SELECT b FROM Booking b where b.booker.id = ?1 AND b.itemId.id =?2 AND b.status = ?3")
    List<Booking> findByBookerAndItem(Long userId, Long itemId, BookingStatus bookingStatus);

}