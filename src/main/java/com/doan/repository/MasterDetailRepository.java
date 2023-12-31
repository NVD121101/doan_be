package com.doan.repository;

import com.doan.entity.Master;
import com.doan.entity.MasterDetail;
import com.doan.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterDetailRepository extends JpaRepository<MasterDetail, Long> {
    @Query("SELECT md FROM MasterDetail md WHERE md.teacherHD = :teacher")
    List<MasterDetail> findMasterDetailsByTeacherHD(@Param("teacher") Optional<Teacher> teacher);

    @Query("SELECT md FROM MasterDetail md WHERE md.teacherPB = :teacher")
    List<MasterDetail> findMasterDetailsByTeacherPB(@Param("teacher") Optional<Teacher> teacher);


    @Query("SELECT md FROM MasterDetail md WHERE (md.scoreArgument < 5 " +
            "OR md.scoreCoucil < 5 ) AND md.master = :master")
    List<MasterDetail> findByScoreArgumentLessThanAndMaster(@Param("master") Master master);

    @Query("SELECT md FROM MasterDetail md WHERE (md.scoreArgument >= 5 " +
            "AND md.scoreCoucil >= 5 ) AND md.master = :master")
    List<MasterDetail> getSuccessfulDefenseStudentsAndMaster(@Param("master") Master master);

    @Query("SELECT md FROM MasterDetail md WHERE (md.scoreArgument < 5 " +
            "OR md.scoreCoucil < 5 )")
    List<MasterDetail> findByScoreArgumentLessThan();

    @Query("SELECT md FROM MasterDetail md WHERE (md.scoreArgument >= 5 " +
            "AND md.scoreCoucil >= 5 )")
    List<MasterDetail> getSuccessfulDefenseStudents();


    @Query(value = "select *\n" +
            "From masters_details\n" +
            "Where masters_details.teacher_hd_id = ? and masters_details.status = \"ACCEPTED\"", nativeQuery = true)
    List<MasterDetail> listUserByUserId(Long teacher_hd_id);

    @Query(value = "select *\n" +
            "From masters_details\n" +
            "Where masters_details.teacher_hd_id = ? and masters_details.status = \"PENDING\"", nativeQuery = true)
    List<MasterDetail> listStudentInviteTeacher(Long teacher_hd_id);

    @Query("SELECT t FROM MasterDetail t LEFT JOIN t.master s " +
            "WHERE (:studentName IS NULL OR t.studentName LIKE %:studentName%) " +
            "AND (:master IS NULL OR s = :master)")
    Page<MasterDetail> findAllByMater(@Param("studentName") String studentName,
                                      @Param("master") Master master,
                                      Pageable pageable);

    @Query(value = "Select Count(*)\n" +
            "from masters_details as m\n" +
            "where m.teacher_hd_id = ? and m.status = \"ACCEPTED\"", nativeQuery = true)
    Long countMasterDetailByTeacherHd(Long teacherHdId);

    @Query(value = "Select Count(*)\n" +
            "from masters_details as m\n" +
            "where m.teacher_pb_id = ? ", nativeQuery = true)
    Long countMasterDetailByTeacherPBId(Long teacherPbId);

    @Query(value = "Select Count(*)\n" +
            "from masters_details as m \n" +
            "where m.score_argument < 5 or m.score_coucil < 5", nativeQuery = true)
    Long countSVLessThan();

    @Query(value = "Select Count(*)\n" +
            "from masters_details as m \n" +
            "where m.score_argument >= 5 and m.score_coucil >=5;", nativeQuery = true)
    Long countSVMoreThan();

    List<MasterDetail> findByMasterMasterId(Long masterId);
}
