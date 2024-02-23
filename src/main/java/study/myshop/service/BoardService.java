package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.board.Comment;
import study.myshop.domain.board.Notice;
import study.myshop.domain.board.Post;
import study.myshop.domain.board.Reply;
import study.myshop.domain.member.Admin;
import study.myshop.domain.member.Member;
import study.myshop.repository.board.CommentRepository;
import study.myshop.repository.board.NoticeRepository;
import study.myshop.repository.board.PostRepository;
import study.myshop.repository.board.ReplyRepository;
import study.myshop.repository.member.AdminRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.member.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long notice(Long adminId, String title, String content) {
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow();
        Notice notice = Notice.createNotice(findAdmin, title, content);
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Transactional
    public Long post(Long memberId, String title, String content) {
        Member findMember = memberRepository.findById(memberId);
        Post post = Post.createPost(title, content, findMember);
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public Long comment(Long memberId, Long postId, String content) {
        Member findMember = memberRepository.findById(memberId);
        Post findPost = postRepository.findById(postId).orElseThrow();
        Comment comment = Comment.createComment(findMember, findPost, content);
        commentRepository.save(comment);
        return comment.getId();
    }

    // TODO: Reply는 좀 더 고민해 볼것
//    @Transactional
//    public Long reply(Long memberId, Long commentId, String content) {
//        Member findMember = memberRepository.findById(memberId);
//        Comment findComment = commentRepository.findById(commentId).orElseThrow();
//        Reply reply = Reply.createReply(findComment);
//    }

    public List<Notice> findNotices() {
        return noticeRepository.findAll();
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public List<Comment> findComments(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

}
